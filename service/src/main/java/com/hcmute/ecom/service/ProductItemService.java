package com.hcmute.ecom.service;

import com.hcmute.ecom.model.ProductItem;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface ProductItemService {
    ResponseEntity<?> insert(ProductItem item);
    ResponseEntity<?> update(ProductItem item, String itemId);
    ResponseEntity<?> updateProductItemProperties(String itemId, int quantity, BigDecimal price, BigDecimal discountPrice);
    ResponseEntity<?> delete(String itemId); // cardId or invoiceId will be null
    ResponseEntity<?> findProductItemById(String itemId);
    ResponseEntity<?> getProductItemsByCartId(String cartId);
    ResponseEntity<?> getProductItemsByInvoiceId(String invoiceId);
}
