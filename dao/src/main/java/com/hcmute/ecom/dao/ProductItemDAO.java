package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.ProductItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface ProductItemDAO {
    /**
     * insert just use for cart table, not invoice
     * */
    int insert(ProductItem item);
    int update(ProductItem item);
    int updateProductItemProperties(String itemId, int quantity, BigDecimal price, BigDecimal discount_price);
    int delete(String itemId); // cardId or invoiceId will be null
    ProductItem findProductItemById(String itemId);
    List<ProductItem> getProductItemsByCartId(String cartId);
    List<ProductItem> getProductItemsByInvoiceId(String invoiceId);
}
