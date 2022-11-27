package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.model.ImportProduct;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
public class ImportProductDTO {
    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ImportProduct transform(Map<String, String> request) {
        ImportProduct ticket = new ImportProduct();
        ticket.setProductId(request.get("productId"));
        ticket.setQuantity(Long.getLong(request.get("quantity")));
        ticket.setImportedPrice(new BigDecimal(request.get("importedPrice")));
        if(request.get("importedDate") != null) {
            ticket.setImportedDate(LocalDateTime.parse("importedDate", DATE_TIME_PATTERN));
        }
        return ticket;
    }
}
