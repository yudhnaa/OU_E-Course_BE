package com.ou.services.impl;

import com.ou.dto.CartDto;
import com.ou.dto.StripeResponseDto;
import com.ou.exceptions.NotFoundException;
import com.ou.pojo.Course;
import com.ou.pojo.PaymentReceipt;
import com.ou.pojo.PaymentReceiptCourse;
import com.ou.repositories.CourseRepository;
import com.ou.repositories.PaymentReceiptRepository;
import com.ou.services.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
@PropertySource("classpath:application.properties")
public class StripeServiceImpl implements StripeService {

    @Autowired
    private Environment env;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private PaymentReceiptRepository paymentReceiptRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PaymentReceiptCourseService paymentReceiptCourseService;

    //stripe -API
    //-> productName , amount , quantity , currency
    //-> return sessionId and url

    public StripeResponseDto checkoutProducts(CartDto cartDto) {
        Stripe.apiKey = env.getProperty("Stripe.apiKey");

        if (cartDto.getCourseIds() == null || cartDto.getCourseIds().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty.");
        }

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        List<Course> courses = new ArrayList<>();

        for (Integer courseId : cartDto.getCourseIds()) {
            Course course = courseRepository.getCourseById(courseId)
                    .orElseThrow(() -> {
                        String msg = localizationService.getMessage(
                                "course.notFoundById", LocaleContextHolder.getLocale());
                        return new NotFoundException(msg);
                    });
            courses.add(course);


            SessionCreateParams.LineItem.PriceData.ProductData productData =
                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName(course.getName())
                            .build();

            SessionCreateParams.LineItem.PriceData priceData =
                    SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency(cartDto.getCurrency() != null ? cartDto.getCurrency() : "USD")
                            .setUnitAmount(course.getPrice().multiply(new BigDecimal(100)).longValue()) // USD cần cents
                            .setProductData(productData)
                            .build();

            SessionCreateParams.LineItem lineItem =
                    SessionCreateParams.LineItem.builder()
                            .setQuantity(1L)
                            .setPriceData(priceData)
                            .build();

            lineItems.add(lineItem);
        }

        // Tạo phiên thanh toán
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl("http://localhost:3000/my-enrollments")
                .addAllLineItem(lineItems)
                .build();

        try {
            Session session = Session.create(params);

            PaymentReceipt paymentReceipt = new PaymentReceipt();
            paymentReceipt.setReceiptId(session.getId());
            paymentReceipt.setAmount(new BigDecimal(session.getAmountTotal()).divide(new BigDecimal(100)));
            paymentReceipt.setCurrency(session.getCurrency());
            paymentReceipt.setStatus("processing");
            paymentReceipt.setPaymentMethod("Stripe");
            Long createdTimestamp = session.getCreated();
            Instant createdInstant = Instant.ofEpochSecond(createdTimestamp);
            paymentReceipt.setCreatedAt(createdInstant);
            paymentReceipt.setUpdatedAt(createdInstant);
            paymentReceipt.setStudent(studentService.getStudentById(Integer.parseInt(cartDto.getStudentId())));

            PaymentReceipt created = paymentReceiptRepository.addPaymentReceipt(paymentReceipt);

            courses.forEach(course -> {
//                PaymentReceiptCourse paymentReceiptCourse = new PaymentReceiptCourse();
//                paymentReceiptCourse.setCourse(course);
//                paymentReceiptCourse.setPaymentReceipt(created);

                paymentReceiptCourseService.addCourseToPaymentReceipt(created,course );
            });

            if (created == null) {
                throw new RuntimeException("Failed to create payment receipt.");
            }

            return StripeResponseDto.builder()
                    .status("SUCCESS")
                    .message("Payment session created")
                    .sessionId(session.getId())
                    .sessionUrl(session.getUrl())
                    .build();

        } catch (StripeException e) {
            return StripeResponseDto.builder()
                    .status("FAILURE")
                    .message("Stripe error: " + e.getMessage())
                    .build();
        }
    }
}
