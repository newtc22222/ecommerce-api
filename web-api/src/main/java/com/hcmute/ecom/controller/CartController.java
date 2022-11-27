package com.hcmute.ecom.controller;

import com.hcmute.ecom.model.Cart;
import com.hcmute.ecom.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
@CrossOrigin("*")
@RequestMapping("/api/v1")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/users/{userId}/cart")
    public ResponseEntity<?> getCartOfUser(@PathVariable("userId") long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/cart")
    public ResponseEntity<?> createNewCart(@RequestBody Cart cart) {
        return cartService.insert(cart);
    }

    @PutMapping("/users/{userId}/cart")
    public ResponseEntity<?> updateCart(@PathVariable("userId") long userId, @RequestBody Cart cart) {
        return cartService.update(cart, userId);
    }

    @DeleteMapping("/users/{userId}/cart")
    public ResponseEntity<?> deleteCart(@PathVariable("userId") long userId) {
        return cartService.delete(userId);
    }
}
