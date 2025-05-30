package com.ou.controllers.restController.paymentController;

import com.ou.dto.CartDto;
import com.ou.dto.StripeResponseDto;
import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.PaymentReceipt;
import com.ou.services.PaymentReceiptService;
import com.ou.services.StripeService;
import com.ou.services.impl.StripeServiceImpl;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@PropertySource("classpath:application.properties")
public class CourseCheckoutController {

    @Autowired
    private StripeService stripeService;
    @Autowired
    private PaymentReceiptService paymentReceiptService;
    @Autowired
    private Environment env;

    @PostMapping("/secure/payment/checkout")
    public ResponseEntity<StripeResponseDto> checkoutProducts(
            @RequestBody CartDto cartDto,
            @AuthenticationPrincipal CustomUserDetails principal
    ) {
        StripeResponseDto response = stripeService.checkoutProducts(cartDto);
        return new ResponseEntity<>(
                response,
                response.getStatus().equalsIgnoreCase("success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @PostMapping("/webhook/stripe")
    public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event = null;
        try {
            event = Webhook.constructEvent(payload, sigHeader, env.getProperty("Stripe.webhookKey"));
        } catch (SignatureVerificationException e) {
            System.out.println("Failed signature verification");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;

        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }



        switch (event.getType()) {
            case "checkout.session.completed":
                if (stripeObject instanceof com.stripe.model.checkout.Session) {
                    com.stripe.model.checkout.Session sessionObj = (com.stripe.model.checkout.Session) stripeObject;
                    String sessionId = sessionObj.getId();

                    Optional<PaymentReceipt> paymentReceiptOpt = paymentReceiptService.getPaymentReceiptByReceiptId(sessionId);
                    if (paymentReceiptOpt.isPresent()) {
                        PaymentReceipt paymentReceipt = paymentReceiptOpt.get();
                        paymentReceipt.setStatus("succeeded"); // hoặc "completed"
                        paymentReceiptService.updatePaymentReceipt(paymentReceipt);
                    } else {
                        // handle receipt not found
                    }
                }
                break;
            case "payment_intent.succeeded":
            case "charge.succeeded":
//                Session sessionObj = (Session) stripeObject;
//                String sessionId = sessionObj.getId();
//                PaymentReceipt paymentReceipt = paymentReceiptService.getPaymentReceiptByReceiptId(sessionId).get();
//                paymentReceipt.setStatus("succeeded");
//                paymentReceiptService.updatePaymentReceipt(paymentReceipt);
                break;
            case "payment_method.attached":
                // ...
                break;
            // ... handle other event types
            default:
                // Unexpected event type
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
