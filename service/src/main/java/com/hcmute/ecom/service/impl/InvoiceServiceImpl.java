package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.model.Invoice;
import com.hcmute.ecom.service.InvoiceService;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public ResponseEntity<?> insert(Invoice invoice) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Invoice invoice) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateStatus(String invoiceId, OrderStatus status) {
        return null;
    }

    @Override
    public ResponseEntity<?> updatePaymentType(String invoiceId, String paymentType) {
        return null;
    }

    @Override
    public ResponseEntity<?> updatePaidStatus(String invoiceId, boolean isPaid) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String invoiceId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllInvoices() {
        return null;
    }

    @Override
    public ResponseEntity<?> getInvoiceById(String invoiceId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getInvoicesByAddress(String address) {
        return null;
    }

    @Override
    public ResponseEntity<?> getInvoicesByDate(LocalDate date) {
        return null;
    }

    @Override
    public ResponseEntity<?> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public ResponseEntity<?> getInvoicesByPaymentType(String paymentType) {
        return null;
    }

    @Override
    public ResponseEntity<?> getInvoicesByOrderStatus(OrderStatus status) {
        return null;
    }

    @Override
    public ResponseEntity<?> getInvoicesByPaidStatus(boolean isPaid) {
        return null;
    }

    @Override
    public ResponseEntity<?> getProductItemsByInvoiceId(String invoiceId) {
        return null;
    }
}
