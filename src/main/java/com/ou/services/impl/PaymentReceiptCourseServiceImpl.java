package com.ou.services.impl;

import com.ou.exceptions.NotFoundException;
import com.ou.pojo.Course;
import com.ou.pojo.PaymentReceipt;
import com.ou.pojo.PaymentReceiptCourse;
import com.ou.repositories.PaymentReceiptCourseRepository;
import com.ou.services.CourseService;
import com.ou.services.LocalizationService;
import com.ou.services.PaymentReceiptCourseService;
import com.ou.services.PaymentReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class PaymentReceiptCourseServiceImpl implements PaymentReceiptCourseService {

    private static final Logger LOGGER = Logger.getLogger(PaymentReceiptCourseServiceImpl.class.getName());

    @Autowired
    private PaymentReceiptCourseRepository paymentReceiptCourseRepo;
    
    @Autowired
    private PaymentReceiptService paymentReceiptService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private LocalizationService localizationService;

    @Override
    public PaymentReceiptCourse addCourseToPaymentReceipt(PaymentReceipt paymentReceipt, Course course) {
        // Check if association already exists
        Optional<PaymentReceiptCourse> existingAssociation = 
                paymentReceiptCourseRepo.getPaymentReceiptCourseByReceiptAndCourse(paymentReceipt.getId(), course.getId());
        
        if (existingAssociation.isPresent()) {
            // Association already exists, return existing
            return existingAssociation.get();
        }
        
        // Create new association
        PaymentReceiptCourse paymentReceiptCourse = new PaymentReceiptCourse();
        paymentReceiptCourse.setPaymentReceipt(paymentReceipt);
        paymentReceiptCourse.setCourse(course);
        
        return paymentReceiptCourseRepo.addPaymentReceiptCourse(paymentReceiptCourse);
    }

    @Override
    public PaymentReceiptCourse addCourseToPaymentReceipt(Integer paymentReceiptId, Integer courseId) {
        // Get payment receipt
        PaymentReceipt paymentReceipt = paymentReceiptService.getPaymentReceiptById(paymentReceiptId);
        
        // Get course
        Course course = courseService.getCourseById(courseId).get();
        
        return addCourseToPaymentReceipt(paymentReceipt, course);
    }

    @Override
    public PaymentReceiptCourse getPaymentReceiptCourseById(Integer id) {
        return paymentReceiptCourseRepo.getPaymentReceiptCourseById(id)
                .orElseThrow(() -> new NotFoundException(
                        localizationService.getMessage("paymentReceiptCourse.notFound", LocaleContextHolder.getLocale())));
    }

    @Override
    public List<PaymentReceiptCourse> getCoursesByPaymentReceipt(Integer paymentReceiptId, Map<String, String> params) {
        // Verify payment receipt exists
        paymentReceiptService.getPaymentReceiptById(paymentReceiptId);
        
        return paymentReceiptCourseRepo.getPaymentReceiptCoursesByPaymentReceipt(paymentReceiptId, params);
    }

    @Override
    public List<PaymentReceiptCourse> getPaymentReceiptsByCourse(Integer courseId, Map<String, String> params) {
        // Verify course exists
        courseService.getCourseById(courseId);
        
        return paymentReceiptCourseRepo.getPaymentReceiptCoursesByCourse(courseId, params);
    }

    @Override
    public boolean isCourseInPaymentReceipt(Integer paymentReceiptId, Integer courseId) {
        return paymentReceiptCourseRepo.getPaymentReceiptCourseByReceiptAndCourse(paymentReceiptId, courseId).isPresent();
    }

    @Override
    public boolean removeCourseFromPaymentReceipt(Integer paymentReceiptId, Integer courseId) {
        // Verify payment receipt exists
        paymentReceiptService.getPaymentReceiptById(paymentReceiptId);
        
        // Verify course exists
        courseService.getCourseById(courseId);
        
        return paymentReceiptCourseRepo.deletePaymentReceiptCourseByReceiptAndCourse(paymentReceiptId, courseId);
    }

    @Override
    public boolean deletePaymentReceiptCourse(Integer id) {
        // Verify the association exists
        getPaymentReceiptCourseById(id);
        
        return paymentReceiptCourseRepo.deletePaymentReceiptCourse(id);
    }

    @Override
    public List<PaymentReceiptCourse> getAllPaymentReceiptCourses(Map<String, String> params) {
        return paymentReceiptCourseRepo.getAllPaymentReceiptCourses(params);
    }

    @Override
    public Long countCoursesByPaymentReceipt(Integer paymentReceiptId) {
        // Verify payment receipt exists
        paymentReceiptService.getPaymentReceiptById(paymentReceiptId);
        
        return paymentReceiptCourseRepo.countPaymentReceiptCoursesByPaymentReceipt(paymentReceiptId);
    }

    @Override
    public Long countPaymentReceiptsByCourse(Integer courseId) {
        // Verify course exists
        courseService.getCourseById(courseId);
        
        return paymentReceiptCourseRepo.countPaymentReceiptCoursesByCourse(courseId);
    }

    @Override
    public Long countAllPaymentReceiptCourses() {
        return paymentReceiptCourseRepo.countAllPaymentReceiptCourses();
    }
}
