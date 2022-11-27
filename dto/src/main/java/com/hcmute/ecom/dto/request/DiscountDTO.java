package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.DiscountType;
import com.hcmute.ecom.model.Discount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
public class DiscountDTO {
    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Discount transform(Map<String, String> request) {
         Discount discount = new Discount();
         discount.setCode(request.get("code"));
         discount.setRate(Float.valueOf(request.get("rate")));

         if (request.get("appliedType") != null){
            discount.setDiscountType(DiscountType.valueOf(request.get("appliedType")));
         }

         discount.setMaxAmount(new BigDecimal(request.get("maxAmount")));
         discount.setAppliedDate(LocalDateTime.parse(request.get("appliedDate"), DATE_TIME_PATTERN));
         discount.setEndedDate(LocalDateTime.parse(request.get("endedDate"), DATE_TIME_PATTERN));
         return discount;
    }
}
