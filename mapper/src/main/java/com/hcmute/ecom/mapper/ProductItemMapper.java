package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.ProductItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class ProductItemMapper implements RowMapper<ProductItem> {
    @Override
    public ProductItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductItem item = new ProductItem();
        item.setId(rs.getString("id"));
        item.setCardId(rs.getString("card_id"));
        item.setInvoiceId(rs.getString("invoice_id"));
        item.setProductId(rs.getString("product_id"));
        item.setItemQuantity(rs.getInt("item_quantity"));
        item.setItemPrice(rs.getBigDecimal("item_price"));
        item.setItemDiscountPrice(rs.getBigDecimal("item_discount_price"));
        return item;
    }
}
