package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setName(rs.getNString("name"));
        category.setImage(rs.getString("image"));
        category.setDescription(rs.getNString("description"));
        return category;
    }
}
