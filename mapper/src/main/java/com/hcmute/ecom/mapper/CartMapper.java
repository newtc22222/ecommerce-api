package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.Cart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class CartMapper implements RowMapper<Cart> {
    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cart cart = new Cart();
        cart.setId(rs.getString("id"));
        cart.setUserId(rs.getLong("user_id"));
        cart.setDiscountId(rs.getLong("discount_id"));
        return cart;
    }
}
