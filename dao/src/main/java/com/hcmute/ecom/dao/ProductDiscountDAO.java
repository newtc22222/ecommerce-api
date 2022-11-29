package com.hcmute.ecom.dao;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
public interface ProductDiscountDAO {
    int insert(String productId, long discountId);
    int delete(String productId, long discountId);
}