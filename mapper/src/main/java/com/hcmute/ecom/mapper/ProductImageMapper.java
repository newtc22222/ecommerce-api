package com.hcmute.ecom.mapper;

import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.ProductImage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class ProductImageMapper implements RowMapper<ProductImage> {
    @Override
    public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductImage image = new ProductImage();
        image.setId(rs.getString("id"));
        image.setProductId(rs.getString("product_id"));
        image.setFeedbackId(rs.getLong("feedback_id"));
        image.setPath(rs.getString("path"));
        image.setImageType(ImageType.valueOf(rs.getString("type")));
        return image;
    }
}
