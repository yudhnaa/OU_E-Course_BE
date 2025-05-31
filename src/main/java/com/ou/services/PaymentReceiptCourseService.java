package com.ou.services;

import com.ou.pojo.Course;
import com.ou.pojo.PaymentReceipt;
import com.ou.pojo.PaymentReceiptCourse;

import java.util.List;
import java.util.Map;

public interface PaymentReceiptCourseService {
    // Add a course to a payment receipt
    PaymentReceiptCourse addCourseToPaymentReceipt(PaymentReceipt paymentReceipt, Course course);
    PaymentReceiptCourse addCourseToPaymentReceipt(Integer paymentReceiptId, Integer courseId);
    
    // Get a specific payment receipt course by ID
    PaymentReceiptCourse getPaymentReceiptCourseById(Integer id);
    
    // Get all courses for a payment receipt
    List<PaymentReceiptCourse> getCoursesByPaymentReceipt(Integer paymentReceiptId, Map<String, String> params);
    
    // Get all payment receipts for a course
    List<PaymentReceiptCourse> getPaymentReceiptsByCourse(Integer courseId, Map<String, String> params);
    
    // Check if a course is already in a payment receipt
    boolean isCourseInPaymentReceipt(Integer paymentReceiptId, Integer courseId);
    
    // Remove a course from a payment receipt
    boolean removeCourseFromPaymentReceipt(Integer paymentReceiptId, Integer courseId);
    
    // Delete a payment receipt course by ID
    boolean deletePaymentReceiptCourse(Integer id);
    
    // Get all payment receipt courses
    List<PaymentReceiptCourse> getAllPaymentReceiptCourses(Map<String, String> params);
    
    // Count methods for pagination
    Long countCoursesByPaymentReceipt(Integer paymentReceiptId);
    Long countPaymentReceiptsByCourse(Integer courseId);
    Long countAllPaymentReceiptCourses();
}
