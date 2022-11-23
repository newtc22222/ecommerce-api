package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.model.ProductItem;
import com.hcmute.ecom.service.ProductItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class ProductItemServiceImpl implements ProductItemService {
    @Override
    public ResponseEntity<?> insert(ProductItem item) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(ProductItem item) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateProductItemProperties(String itemId, int quantity, BigDecimal price, BigDecimal discount_price) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String itemId) {
        return null;
    }

    @Override
    public ResponseEntity<?> findProductItemById(String itemId) {
        return null;
    }
}
