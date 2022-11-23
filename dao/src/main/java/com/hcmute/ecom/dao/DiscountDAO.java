package com.hcmute.ecom.dao;

import com.hcmute.ecom.enums.DiscountType;
import com.hcmute.ecom.model.Discount;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface DiscountDAO {
    int insert(Discount discount);
    int update(Discount discount);
    int delete(long discountId);
    List<Discount> getAllDiscounts();
    Discount findDiscountById(long discountId);
    List<Discount> findDiscountsByCode(String code);
    List<Discount> getDiscountsByProduct(String productId);
    List<Discount> getDiscountsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Discount> getDiscountsByType(DiscountType type);
}
