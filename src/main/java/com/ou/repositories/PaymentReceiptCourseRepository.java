package com.ou.repositories;

import com.ou.pojo.PaymentReceiptCourse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PaymentReceiptCourseRepository {
    // Create operation
    PaymentReceiptCourse addPaymentReceiptCourse(PaymentReceiptCourse paymentReceiptCourse);
    
    // Read operations
    Optional<PaymentReceiptCourse> getPaymentReceiptCourseById(Integer id);
    List<PaymentReceiptCourse> getPaymentReceiptCoursesByPaymentReceipt(Integer paymentReceiptId, Map<String, String> params);
    List<PaymentReceiptCourse> getPaymentReceiptCoursesByCourse(Integer courseId, Map<String, String> params);
    Optional<PaymentReceiptCourse> getPaymentReceiptCourseByReceiptAndCourse(Integer paymentReceiptId, Integer courseId);
    List<PaymentReceiptCourse> getAllPaymentReceiptCourses(Map<String, String> params);
    
    // Delete operations
    boolean deletePaymentReceiptCourse(Integer id);
    boolean deletePaymentReceiptCourseByReceiptAndCourse(Integer paymentReceiptId, Integer courseId);
    
    // Count methods for pagination
    Long countPaymentReceiptCoursesByPaymentReceipt(Integer paymentReceiptId);
    Long countPaymentReceiptCoursesByCourse(Integer courseId);
    Long countAllPaymentReceiptCourses();
}
