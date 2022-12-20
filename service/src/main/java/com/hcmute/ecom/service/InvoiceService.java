package com.hcmute.ecom.service;

import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.model.Invoice;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface InvoiceService {
    ResponseEntity<?> insert(Invoice invoice);
    ResponseEntity<?> update(Invoice invoice, String invoiceId);
    ResponseEntity<?> updateStatus(String invoiceId, OrderStatus status);
    ResponseEntity<?> updateInvoicePaymentMethodAndPaidStatus(String invoiceId, String paymentType, boolean isPaid);
    ResponseEntity<?> updatePaidStatus(String invoiceId, boolean isPaid); // true -> was paid
    ResponseEntity<?> delete(String invoiceId);

    ResponseEntity<?> getAllInvoices();
    ResponseEntity<?> getInvoiceById(String invoiceId);
    ResponseEntity<?> getInvoicesByUserId(long userId);

    ResponseEntity<?> filter(Map<String, String> params);
}
