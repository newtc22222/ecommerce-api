package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.model.Invoice;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
public class InvoiceDTO {
    public static Invoice transform(Map<String, String> request) {
        Invoice invoice = new Invoice();
        if (request.get("id") != null) {
            invoice.setId(request.get("id"));
        }
        invoice.setUserId(Long.getLong(request.get("userId")));
        invoice.setStockQuantity(Integer.getInteger(request.get("stockQuantity")));
        invoice.setAddress(request.get("address"));
        if (request.get("shipFee") != null) {
            invoice.setShipFee(Double.valueOf(request.get("shipFee")));
        }
        invoice.setTotalCost(new BigDecimal(request.get("totalCost")));
        invoice.setDiscountId(Long.getLong(request.get("discountId")));
        if (request.get("tax") != null) {
            invoice.setTax(new BigDecimal(request.get("tax")));
        }
        invoice.setFinalTotalCost(new BigDecimal(request.get("finalTotalCost")));
        invoice.setPaymentType(request.get("paymentType"));
        invoice.setStatus(OrderStatus.valueOf(request.get("status")));
        invoice.setPaid(Boolean.getBoolean(request.get("isPaid")));
        if (request.get("note") != null) {
            invoice.setNote(request.get("note"));
        }
        if (request.get("troubleReason") != null) {
            invoice.setTroubleReason(request.get("troubleReason"));
        }
        return invoice;
    }
}
