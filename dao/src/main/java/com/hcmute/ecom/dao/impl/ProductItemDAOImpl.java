package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.ProductItemDAO;
import com.hcmute.ecom.mapper.ProductItemMapper;
import com.hcmute.ecom.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Component
public class ProductItemDAOImpl implements ProductItemDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_product_item";
    private final String INSERT = String.format("insert into %s values (?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);
    // Use with transform (cart -> invoice)
    private final String UPDATE = String.format("update %s " +
            "set cart_id=?, invoice_id=?, product_id=?, item_quantity=?, item_price=?, item_discount_price=? where id=?", TABLE_NAME);
    private final String UPDATE_PRODUCT_ITEM_PROPERTIES = String.format("update %s " +
            "set item_quantity=?, item_price=?, item_discount_price=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

//    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);
    private final String QUERY_PRODUCT_ITEMS_BY_CART_ID =
            String.format("select * from %s where cart_id=?", TABLE_NAME);
    private final String QUERY_PRODUCT_ITEMS_BY_INVOICE_ID =
            String.format("select * from %s where invoice_id=?", TABLE_NAME);

    @Override
    public int insert(ProductItem item) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    item.getId(),
                    item.getCartId(),
                    item.getInvoiceId(),
                    item.getProductId(),
                    item.getItemQuantity(),
                    item.getItemPrice().doubleValue(),
                    item.getItemDiscountPrice().doubleValue()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(ProductItem item) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    item.getCartId(),
                    item.getInvoiceId(),
                    item.getProductId(),
                    item.getItemQuantity(),
                    item.getItemPrice().doubleValue(),
                    item.getItemDiscountPrice().doubleValue(),
                    item.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int updateProductItemProperties(String itemId, int quantity, BigDecimal price, BigDecimal discount_price) {
        try {
            return jdbcTemplate.update(
                    UPDATE_PRODUCT_ITEM_PROPERTIES,
                    quantity,
                    price.doubleValue(),
                    discount_price.doubleValue(),
                    itemId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(String itemId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    itemId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public ProductItem findProductItemById(String itemId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new ProductItemMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<ProductItem> getProductItemsByCartId(String cartId) {
        try {
            return jdbcTemplate.query(
                    QUERY_PRODUCT_ITEMS_BY_CART_ID,
                    new ProductItemMapper(),
                    cartId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<ProductItem> getProductItemsByInvoiceId(String invoiceId) {
        try {
            return jdbcTemplate.query(
                    QUERY_PRODUCT_ITEMS_BY_INVOICE_ID,
                    new ProductItemMapper(),
                    invoiceId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
