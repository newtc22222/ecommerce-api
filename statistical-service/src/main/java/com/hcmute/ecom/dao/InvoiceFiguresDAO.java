package com.hcmute.ecom.dao;

import com.hcmute.ecom.enums.OrderStatus;

import java.time.LocalDate;

/**
 * @author Nhat Phi
 * @since 2022-12-01
 * */
public interface InvoiceFiguresDAO {
    int getTotalInvoice();
    int getTotalInvoiceInDate(LocalDate date);
//    int getTotalInvoiceInDateRange(LocalDate startDate, LocalDate endDate);
    int getTotalInvoiceByOrderStatus(OrderStatus status);
    int getTotalInvoiceInDateByOrderStatus(LocalDate date, OrderStatus status);
    int getTotalInvoiceOfUser(long userId);
}
