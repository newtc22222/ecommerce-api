package com.hcmute.ecom.dao;

/**
 * @author Nhat Phi
 * @since 2022-12-01
 * */
public interface StockFiguresDAO {
    int getTotalQuantityProductInStock();
    int getTotalQuantityProductInStockByProductId(String productId);
}
