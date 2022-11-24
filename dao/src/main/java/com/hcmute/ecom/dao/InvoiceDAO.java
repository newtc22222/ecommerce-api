package com.hcmute.ecom.dao;

import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.model.Invoice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface InvoiceDAO {
    int insert(Invoice invoice);
    int update(Invoice invoice);
    int updateStatus(String invoiceId, OrderStatus status);
    int updatePaymentType(String invoiceId, String paymentType);
    int updatePaidStatus(String invoiceId, boolean isPaid); // true -> was paid
    int delete(String invoiceId);
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(String invoiceId);
    List<Invoice> getInvoicesByUserId(long userId);
    List<Invoice> getInvoicesByAddress(String address);
    List<Invoice> getInvoicesByDate(LocalDate date);
    List<Invoice> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Invoice> getInvoicesByPaymentType(String paymentType);
    List<Invoice> getInvoicesByOrderStatus(OrderStatus status);
    List<Invoice> getInvoicesByPaidStatus(boolean isPaid); // true -> was paid
}
