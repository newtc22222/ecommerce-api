package com.hcmute.ecom.controller;

import com.hcmute.ecom.model.Cart;
import com.hcmute.ecom.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
@Api(tags = "Store product item of user", value = "Cart Controller")
@CrossOrigin(value = {"*"})
@RequestMapping("/api/v1")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @ApiOperation(value = "Get cart of user (Only 1 cart)", response = Cart.class)
    @GetMapping("/users/{userId}/cart")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCartOfUser(@PathVariable("userId") long userId) {
        return cartService.getCartByUserId(userId);
    }

    @ApiOperation(value = "Create new cart for user", response = ResponseEntity.class)
    @PostMapping("/cart")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createNewCart(@RequestBody Cart cart) {
        return cartService.insert(cart);
    }

    @ApiOperation(value = "Update cart information", response = ResponseEntity.class)
    @PutMapping("/users/{userId}/cart")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateCart(@PathVariable("userId") long userId, @RequestBody Cart cart) {
        return cartService.update(cart, userId);
    }

    @ApiOperation(value = "Remove old cart (or transform to invoice table)", response = ResponseEntity.class)
    @DeleteMapping("/users/{userId}/cart")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteCart(@PathVariable("userId") long userId) {
        return cartService.delete(userId);
    }
}
