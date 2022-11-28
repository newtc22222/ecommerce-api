package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.model.Invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
public class InvoiceDTO {
    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Invoice transform(Map<String, String> request) {
        Invoice invoice = new Invoice();
        if (request.containsKey("id")) {
            invoice.setId(request.get("id"));
        }
        invoice.setUserId(Long.getLong(request.get("userId")));
        invoice.setStockQuantity(Integer.getInteger(request.get("stockQuantity")));
        invoice.setAddress(request.get("address"));
        if (request.containsKey("shipFee")) {
            invoice.setShipFee(Double.valueOf(request.get("shipFee")));
        }
        invoice.setTotalCost(new BigDecimal(request.get("totalCost")));
        invoice.setDiscountId(Long.getLong(request.get("discountId")));
        if (request.containsKey("tax")) {
            invoice.setTax(new BigDecimal(request.get("tax")));
        }
        invoice.setFinalTotalCost(new BigDecimal(request.get("finalTotalCost")));
        if (request.containsKey("createdDate")) {
            invoice.setCreatedDate(LocalDateTime.parse(request.get("createdDate"), DATE_TIME_PATTERN));
        }
        invoice.setPaymentType(request.get("paymentType"));
        invoice.setStatus(OrderStatus.valueOf(request.get("status")));
        invoice.setPaid(Boolean.getBoolean(request.get("isPaid")));
        if (request.containsKey("note")) {
            invoice.setNote(request.get("note"));
        }
        if (request.containsKey("troubleReason")) {
            invoice.setTroubleReason(request.get("troubleReason"));
        }
        return invoice;
    }
}
