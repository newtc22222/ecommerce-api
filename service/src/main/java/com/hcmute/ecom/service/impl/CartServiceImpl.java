package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.CartDAO;
import com.hcmute.ecom.model.Cart;
import com.hcmute.ecom.service.CartService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDAO cartDAO;
    
    @Override
    public ResponseEntity<?> insert(Cart cart) {
        return ResponseCUDObject.of(
                cartDAO.insert(cart) > 0,
                HttpStatus.CREATED,
                "Insert new cart successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new cart! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(Cart cart, long userId) {
        Cart oldCart = cartDAO.findCartByUserId(userId);
        
        if(oldCart == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find cart of user with id = " + userId
                    ));
        }
        else {
            oldCart.setDiscountId(cart.getDiscountId());
        }
        
        return ResponseCUDObject.of(
                cartDAO.update(oldCart) > 0,
                HttpStatus.OK,
                "Update cart successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update cart! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long userId) {
        Cart cart = cartDAO.findCartByUserId(userId);

        return ResponseCUDObject.of(
                cartDAO.delete(cart.getId(), userId) > 0,
                HttpStatus.OK,
                "Delete cart successfully!",
                HttpStatus.NOT_FOUND,
                "Failed to delete cart! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> getCartByUserId(long userId) {
        Cart cart = cartDAO.findCartByUserId(userId);
        if(cart == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find cart of user with id = " + userId));
        }

        return ResponseEntity.ok(cart);
    }
}
