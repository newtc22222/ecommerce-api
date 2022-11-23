package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.CartDAO;
import com.hcmute.ecom.mapper.CartMapper;
import com.hcmute.ecom.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
@Component
public class CartDAOImpl implements CartDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_cart";
    private final String INSERT = String.format("insert into %s values (?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set discount_id=? where id=? and user_id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=? and user_id=?", TABLE_NAME);

//    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
//    private final String QUERY_ONE_BY_ID = String.format("select * from %s where _id=? limit 1", TABLE_NAME);
    private final String QUERY_ONE_BY_USER_ID = String.format("select * from %s where user_id=?", TABLE_NAME);

    @Override
    public int insert(Cart cart) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    cart.getId(),
                    cart.getUserId(),
                    cart.getDiscountId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(Cart cart) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    cart.getDiscountId(),
                    cart.getId(),
                    cart.getUserId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(String cartId, long userId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    cartId,
                    userId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public Cart findCartByUserId(long userId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_USER_ID,
                    new CartMapper(),
                    userId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
