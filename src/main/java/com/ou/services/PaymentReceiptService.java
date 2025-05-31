package com.ou.services;

import com.ou.pojo.PaymentReceipt;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PaymentReceiptService {
    // Retrieve a payment receipt by ID
    PaymentReceipt getPaymentReceiptById(Integer id);
    
    // Add a new payment receipt
    PaymentReceipt addPaymentReceipt(PaymentReceipt paymentReceipt);
    
    // Update an existing payment receipt
    PaymentReceipt updatePaymentReceipt(PaymentReceipt paymentReceipt);
    
    // Delete a payment receipt by ID
    boolean deletePaymentReceipt(Integer id);
    
    // Retrieve a list of payment receipts with optional filters and pagination
    List<PaymentReceipt> getPaymentReceipts(Map<String, String> params);
    
    // Search for payment receipts based on filters and pagination
    List<PaymentReceipt> searchPaymentReceipts(Map<String, String> filters, Map<String, String> params);
    
    // Retrieve payment receipts by student ID
    List<PaymentReceipt> getPaymentReceiptsByStudent(Long studentId, Map<String, String> params);
    
    // Retrieve payment receipts by status
    List<PaymentReceipt> getPaymentReceiptsByStatus(String status, Map<String, String> params);
    
    // Count total payment receipts
    long countPaymentReceipts();
    
    // Count payment receipts by student
    long countPaymentReceiptsByStudent(Long studentId);
    
    // Count payment receipts by status
    long countPaymentReceiptsByStatus(String status);
    
    // Count search results based on filters
    long countSearchResults(Map<String, String> filters);
    
    // Get payment receipt by receipt ID
    Optional<PaymentReceipt> getPaymentReceiptByReceiptId(String receiptId);
}
