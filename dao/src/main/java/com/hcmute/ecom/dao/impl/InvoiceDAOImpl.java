package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.InvoiceDAO;
import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.mapper.InvoiceMapper;
import com.hcmute.ecom.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
@Component
public class InvoiceDAOImpl implements InvoiceDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_invoice";
    private final String INSERT =
            String.format("insert into %s values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set user_id=?, stock_quantity=?, address=?, ship_fee=?, total_cost=?, discount_id=?, " +
            "tax=?, final_total_cost=?, created_date=?, payment_type=?, status=?, is_paid=?, " +
            "note=?, trouble_reason=?  where id=?", TABLE_NAME);
    private final String UPDATE_ORDER_STATUS = String.format("update %s set status=? where id=?", TABLE_NAME);
    private final String UPDATE_PAYMENT_TYPE = String.format("update %s set payment_type=? where id=?", TABLE_NAME);
    private final String UPDATE_PAID_STATUS = String.format("update %s set is_paid=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);

    private final String QUERY_INVOICES_BY_USER_ID =
            String.format("select * from %s where user_id=?", TABLE_NAME);
    private final String QUERY_INVOICES_BY_ADDRESS =
            String.format("select * from %s where address like ?", TABLE_NAME);
    private final String QUERY_INVOICES_BY_DATE =
            String.format("select * from %s where cast(created_date as date) = ?", TABLE_NAME);
    private final String QUERY_INVOICES_BY_DATE_RANGE =
            String.format("select * from %s where created_date between ? and ?", TABLE_NAME);
    private final String QUERY_INVOICES_BY_PAYMENT_TYPE =
            String.format("select * from %s where payment_type=?", TABLE_NAME);
    private final String QUERY_INVOICES_BY_ORDER_STATUS =
            String.format("select * from %s where status=?", TABLE_NAME);
    private final String QUERY_INVOICES_BY_PAID_STATUS =
            String.format("select * from %s where is_paid=?", TABLE_NAME);

    @Override
    public int insert(Invoice invoice) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    invoice.getId(),
                    invoice.getUserId(),
                    invoice.getStockQuantity(),
                    invoice.getAddress(),
                    invoice.getShipFee(),
                    invoice.getTotalCost().doubleValue(),
                    invoice.getDiscountId(),
                    invoice.getTax().doubleValue(),
                    invoice.getFinalTotalCost().doubleValue(),
                    Timestamp.valueOf(invoice.getCreatedDate()),
                    invoice.getPaymentType(),
                    invoice.getStatus().toString(),
                    invoice.getPaid(),
                    invoice.getNote(),
                    invoice.getTroubleReason()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(Invoice invoice) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    invoice.getUserId(),
                    invoice.getStockQuantity(),
                    invoice.getAddress(),
                    invoice.getShipFee(),
                    invoice.getTotalCost().doubleValue(),
                    invoice.getDiscountId(),
                    invoice.getTax().doubleValue(),
                    invoice.getFinalTotalCost().doubleValue(),
                    Timestamp.valueOf(invoice.getCreatedDate()),
                    invoice.getPaymentType(),
                    invoice.getStatus().toString(),
                    invoice.getPaid(),
                    invoice.getNote(),
                    invoice.getTroubleReason(),
                    invoice.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int updateStatus(String invoiceId, OrderStatus status) {
        try {
            return jdbcTemplate.update(
                    UPDATE_ORDER_STATUS,
                    status.toString(),
                    invoiceId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int updatePaymentType(String invoiceId, String paymentType) {
        try {
            return jdbcTemplate.update(
                    UPDATE_PAYMENT_TYPE,
                    paymentType,
                    invoiceId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int updatePaidStatus(String invoiceId, boolean isPaid) {
        try {
            return jdbcTemplate.update(
                    UPDATE_PAID_STATUS,
                    isPaid,
                    invoiceId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(String invoiceId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    invoiceId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new InvoiceMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Invoice getInvoiceById(String invoiceId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new InvoiceMapper(),
                    invoiceId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Invoice> getInvoicesByUserId(long userId) {
        try {
            return jdbcTemplate.query(
                    QUERY_INVOICES_BY_USER_ID,
                    new InvoiceMapper(),
                    userId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Invoice> getInvoicesByAddress(String address) {
        try {
            return jdbcTemplate.query(
                    QUERY_INVOICES_BY_ADDRESS,
                    new InvoiceMapper(),
                    "%" + address + "%"
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Invoice> getInvoicesByDate(LocalDate date) {
        try {
            return jdbcTemplate.query(
                    QUERY_INVOICES_BY_DATE,
                    new InvoiceMapper(),
                    Date.valueOf(date)
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Invoice> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        try {
            return jdbcTemplate.query(
                    QUERY_INVOICES_BY_DATE_RANGE,
                    new InvoiceMapper(),
                    startDate,
                    endDate
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Invoice> getInvoicesByPaymentType(String paymentType) {
        try {
            return jdbcTemplate.query(
                    QUERY_INVOICES_BY_PAYMENT_TYPE,
                    new InvoiceMapper(),
                    paymentType
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Invoice> getInvoicesByOrderStatus(OrderStatus status) {
        try {
            return jdbcTemplate.query(
                    QUERY_INVOICES_BY_ORDER_STATUS,
                    new InvoiceMapper(),
                    status.toString()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Invoice> getInvoicesByPaidStatus(boolean isPaid) {
        try {
            return jdbcTemplate.query(
                    QUERY_INVOICES_BY_PAID_STATUS,
                    new InvoiceMapper(),
                    isPaid
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
