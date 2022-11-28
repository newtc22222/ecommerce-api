package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.laptop.Laptop;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class ProductMapper implements RowMapper<Laptop> {
    @Override
    public Laptop mapRow(ResultSet rs, int rowNum) throws SQLException {
        Laptop product = new Laptop();
        product.setId(rs.getString("_id"));
        product.setName(rs.getNString("name"));
        product.setBrandId(rs.getLong("brand_id"));
        product.setCategoryId(rs.getLong("category_id"));
        product.setReleasedDate(rs.getDate("released_date"));
        product.setQuantityInStock(rs.getInt("quantity_in_stock"));
        product.setListedPrice(rs.getBigDecimal("listed_price"));
        product.setPrice(rs.getBigDecimal("price"));

        // Laptop
        product.setRamCapacity(rs.getByte("ram_capacity"));
        product.setCpuBrand(rs.getString("cpu_brand"));
        product.setCpuType(rs.getString("cpu_type"));
        product.setCpuMoreInformationHTML(rs.getNString("cpu_more_infor_html"));
        product.setMoreDescriptionHTML(rs.getNString("more_description_html"));
        product.setScreenId(rs.getLong("screen_id"));
        return product;
    }
}
