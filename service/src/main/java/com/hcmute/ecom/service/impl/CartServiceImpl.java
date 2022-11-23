package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.model.Cart;
import com.hcmute.ecom.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class CartServiceImpl implements CartService {
    @Override
    public ResponseEntity<?> insert(Cart cart) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Cart cart) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String cartId, long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getProductItemsByCartId(String cartId) {
        return null;
    }
}
