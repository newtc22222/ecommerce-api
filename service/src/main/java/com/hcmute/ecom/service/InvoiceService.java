package com.hcmute.ecom.service;

import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.model.Invoice;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface InvoiceService {
    ResponseEntity<?> insert(Invoice invoice);
    ResponseEntity<?> update(Invoice invoice, String invoiceId);
    ResponseEntity<?> updateStatus(String invoiceId, OrderStatus status);
    ResponseEntity<?> updatePaymentType(String invoiceId, String paymentType);
    ResponseEntity<?> updatePaidStatus(String invoiceId, boolean isPaid); // true -> was paid
    ResponseEntity<?> delete(String invoiceId);

    ResponseEntity<?> getAllInvoices();
    ResponseEntity<?> getInvoiceById(String invoiceId);
    ResponseEntity<?> getInvoicesByAddress(String address);
    ResponseEntity<?> getInvoicesByDate(LocalDate date);
    ResponseEntity<?> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    ResponseEntity<?> getInvoicesByPaymentType(String paymentType);
    ResponseEntity<?> getInvoicesByOrderStatus(OrderStatus status);
    ResponseEntity<?> getInvoicesByPaidStatus(boolean isPaid);

    ResponseEntity<?> getProductItemsByInvoiceId(String invoiceId);
}
