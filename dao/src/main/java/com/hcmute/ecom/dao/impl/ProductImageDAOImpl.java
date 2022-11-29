package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.ProductImageDAO;
import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.mapper.ProductImageMapper;
import com.hcmute.ecom.model.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Component
public class ProductImageDAOImpl implements ProductImageDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_product_image";
    private final String INSERT = String.format("insert into %s values (?, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set feedback_id=?, path=?, type=? where id=? and product_id=?", TABLE_NAME);
    private final String UPDATE_PATH_AND_TYPE = String.format("update %s set path=?, type=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);
    private final String QUERY_PRODUCT_IMAGES_BY_PRODUCT_ID =
            String.format("select * from %s where product_id=?", TABLE_NAME);
    private final String QUERY_PRODUCT_IMAGES_BY_IMAGE_TYPE =
            String.format("select * from %s where type=?", TABLE_NAME);
    private final String QUERY_PRODUCT_IMAGES_BY_PRODUCT_ID_AND_IMAGE_TYPE =
            String.format("select * from %s where product_id=? and type=?", TABLE_NAME);

    @Override
    public int insert(ProductImage image) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    image.getId(),
                    image.getProductId(),
                    image.getFeedbackId(),
                    image.getPath(),
                    image.getImageType().toString()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(ProductImage image) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    image.getFeedbackId(),
                    image.getPath(),
                    image.getImageType().toString(),
                    image.getId(),
                    image.getProductId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int updatePathAndType(String imageId, String path, ImageType type) {
        try {
            return jdbcTemplate.update(
                    UPDATE_PATH_AND_TYPE,
                    path,
                    type.toString(),
                    imageId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(String imageId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    imageId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<ProductImage> getAllProductImages() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new ProductImageMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public ProductImage findProductImageById(String imageId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new ProductImageMapper(),
                    imageId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<ProductImage> getProductImagesByProductId(String productId) {
        try {
            return jdbcTemplate.query(
                    QUERY_PRODUCT_IMAGES_BY_PRODUCT_ID,
                    new ProductImageMapper(),
                    productId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<ProductImage> getProductImagesByImageType(ImageType type) {
        try {
            return jdbcTemplate.query(
                    QUERY_PRODUCT_IMAGES_BY_IMAGE_TYPE,
                    new ProductImageMapper(),
                    type
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<ProductImage> getProductImagesByProductIdAndImageType(String productId, ImageType type) {
        try {
            return jdbcTemplate.query(
                    QUERY_PRODUCT_IMAGES_BY_PRODUCT_ID_AND_IMAGE_TYPE,
                    new ProductImageMapper(),
                    productId,
                    type.toString()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
