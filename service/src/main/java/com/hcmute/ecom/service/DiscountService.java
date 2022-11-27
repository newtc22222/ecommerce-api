package com.hcmute.ecom.service;

import com.hcmute.ecom.enums.DiscountType;
import com.hcmute.ecom.model.Discount;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface DiscountService {
    ResponseEntity<?> insert(Discount discount);
    ResponseEntity<?> update(Discount discount, long discountId);
    ResponseEntity<?> delete(long discountId);

    ResponseEntity<?> getAllDiscounts();
    ResponseEntity<?> getDiscountById(long discountId);
    ResponseEntity<?> getDiscountsByProduct(String productId);

    // Filter
    ResponseEntity<?> findDiscountsByCode(String code);
    ResponseEntity<?> getDiscountByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    ResponseEntity<?> getDiscountByType(DiscountType type);
}
