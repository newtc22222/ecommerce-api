package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.Cart;

/**
 * @author Nhat Phi
 * @since 2022-11-20
 * */
public interface CartDAO {
    int insert(Cart cart);
    int update(Cart cart);
    int delete(long userId);
//    List<Cart> getAllCart();
    Cart findCartByUserId(long userId);
}
