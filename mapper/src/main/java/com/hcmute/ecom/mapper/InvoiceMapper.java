package com.hcmute.ecom.mapper;

import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.model.Invoice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class InvoiceMapper implements RowMapper<Invoice> {
    @Override
    public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getString("id"));
        invoice.setUserId(rs.getLong("user_id"));
        invoice.setStockQuantity(rs.getInt("stock_quantity"));
        invoice.setAddress(rs.getNString("address"));
        invoice.setShipFee(rs.getDouble("ship_fee"));
        invoice.setTotalCost(rs.getBigDecimal("total_cost"));
        invoice.setDiscountId(rs.getLong("discount_id"));
        invoice.setTax(rs.getBigDecimal("tax"));
        invoice.setFinalTotalCost(rs.getBigDecimal("final_total_cost"));
        invoice.setCreatedDate(LocalDateTime.parse(rs.getString("created_date")));
        invoice.setPaymentType(rs.getNString("payment_type"));
        invoice.setStatus(OrderStatus.valueOf(rs.getString("status")));
        invoice.setPaid(rs.getBoolean("is_paid"));
        invoice.setNote(rs.getNString("note"));
        invoice.setTroubleReason(rs.getNString("trouble_reason"));
        return invoice;
    }
}
