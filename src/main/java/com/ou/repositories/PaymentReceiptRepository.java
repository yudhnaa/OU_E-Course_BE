package com.ou.repositories;

import com.ou.pojo.PaymentReceipt;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PaymentReceiptRepository {
    // Create operations
    PaymentReceipt addPaymentReceipt(PaymentReceipt paymentReceipt);

    // Read operations with pagination
    List<PaymentReceipt> getPaymentReceipts(Map<String, String> params);
    List<PaymentReceipt> searchPaymentReceipts(Map<String, String> filters, Map<String, String> params);

    // Return an Optional<PaymentReceipt> to handle cases where the receipt is not found to avoid null checks
    Optional<PaymentReceipt> getPaymentReceiptById(Integer id);
    Optional<PaymentReceipt> getPaymentReceiptByReceiptId(String receiptId);
    List<PaymentReceipt> getPaymentReceiptsByStudent(Long studentId, Map<String, String> params);
    List<PaymentReceipt> getPaymentReceiptsByStatus(String status, Map<String, String> params);

    // Update operation
    PaymentReceipt updatePaymentReceipt(PaymentReceipt paymentReceipt);

    // Delete operation
    boolean deletePaymentReceipt(Integer id);

    // Count methods for pagination
    long countPaymentReceipts();
    long countPaymentReceiptsByStudent(Long studentId);
    long countPaymentReceiptsByStatus(String status);
    long countSearchResults(Map<String, String> filters);
}
