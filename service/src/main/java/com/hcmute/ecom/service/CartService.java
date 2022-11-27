package com.hcmute.ecom.service;

import com.hcmute.ecom.model.Cart;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface CartService {
    ResponseEntity<?> insert(Cart cart);
    ResponseEntity<?> update(Cart cart, long userId);
    ResponseEntity<?> delete(long userId);
    ResponseEntity<?> getCartByUserId(long userId);
}
