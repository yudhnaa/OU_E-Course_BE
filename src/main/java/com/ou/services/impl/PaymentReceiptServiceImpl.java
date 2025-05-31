package com.ou.services.impl;

import com.ou.exceptions.NotFoundException;
import com.ou.pojo.PaymentReceipt;
import com.ou.repositories.PaymentReceiptRepository;
import com.ou.services.LocalizationService;
import com.ou.services.PaymentReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class PaymentReceiptServiceImpl implements PaymentReceiptService {

    private static final Logger LOGGER = Logger.getLogger(PaymentReceiptServiceImpl.class.getName());

    @Autowired
    private PaymentReceiptRepository paymentReceiptRepo;
    
    @Autowired
    private LocalizationService localizationService;

    @Override
    public PaymentReceipt getPaymentReceiptById(Integer id) {
        return paymentReceiptRepo.getPaymentReceiptById(id)
                .orElseThrow(() -> new NotFoundException(
                        localizationService.getMessage("paymentReceipt.notFound", LocaleContextHolder.getLocale())));
    }

    @Override
    public PaymentReceipt addPaymentReceipt(PaymentReceipt paymentReceipt) {
        // Set creation timestamp
        paymentReceipt.setCreatedAt(Instant.now());
        paymentReceipt.setUpdatedAt(Instant.now());
        
        // If status is not set, set it to pending
        if (paymentReceipt.getStatus() == null || paymentReceipt.getStatus().isEmpty()) {
            paymentReceipt.setStatus("pending");
        }
        
        return paymentReceiptRepo.addPaymentReceipt(paymentReceipt);
    }

    @Override
    public PaymentReceipt updatePaymentReceipt(PaymentReceipt paymentReceipt) {
        // Get existing payment receipt
        Optional<PaymentReceipt> existingReceiptOpt = paymentReceiptRepo.getPaymentReceiptById(paymentReceipt.getId());
        
        if (existingReceiptOpt.isPresent()) {
            // Update timestamp
            paymentReceipt.setUpdatedAt(Instant.now());
            // Preserve creation timestamp
            paymentReceipt.setCreatedAt(existingReceiptOpt.get().getCreatedAt());
            
            return paymentReceiptRepo.updatePaymentReceipt(paymentReceipt);
        } else {
            throw new NotFoundException(
                    localizationService.getMessage("paymentReceipt.notFound", LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public boolean deletePaymentReceipt(Integer id) {
        if (paymentReceiptRepo.getPaymentReceiptById(id).isPresent()) {
            return paymentReceiptRepo.deletePaymentReceipt(id);
        } else {
            throw new NotFoundException(
                    localizationService.getMessage("paymentReceipt.notFound", LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public List<PaymentReceipt> getPaymentReceipts(Map<String, String> params) {
        return paymentReceiptRepo.getPaymentReceipts(params);
    }

    @Override
    public List<PaymentReceipt> searchPaymentReceipts(Map<String, String> filters, Map<String, String> params) {
        return paymentReceiptRepo.searchPaymentReceipts(filters, params);
    }

    @Override
    public List<PaymentReceipt> getPaymentReceiptsByStudent(Long studentId, Map<String, String> params) {
        return paymentReceiptRepo.getPaymentReceiptsByStudent(studentId, params);
    }

    @Override
    public List<PaymentReceipt> getPaymentReceiptsByStatus(String status, Map<String, String> params) {
        return paymentReceiptRepo.getPaymentReceiptsByStatus(status, params);
    }

    @Override
    public long countPaymentReceipts() {
        return paymentReceiptRepo.countPaymentReceipts();
    }

    @Override
    public long countPaymentReceiptsByStudent(Long studentId) {
        return paymentReceiptRepo.countPaymentReceiptsByStudent(studentId);
    }

    @Override
    public long countPaymentReceiptsByStatus(String status) {
        return paymentReceiptRepo.countPaymentReceiptsByStatus(status);
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return paymentReceiptRepo.countSearchResults(filters);
    }

    @Override
    public Optional<PaymentReceipt> getPaymentReceiptByReceiptId(String receiptId) {
        return paymentReceiptRepo.getPaymentReceiptByReceiptId(receiptId);
    }
}
