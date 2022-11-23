package com.hcmute.ecom.dao;

import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.ProductImage;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface ProductImageDAO {
    int insert(ProductImage image);
    int update(ProductImage image);
    int updatePathAndType(String imageId, String path, ImageType type);
    int delete(String imageId);
    List<ProductImage> getAllProductImages();
    ProductImage findProductImageById(String imageId);
    List<ProductImage> getProductImagesByProductId(String productId);
    List<ProductImage> getProductImagesByImageType(ImageType type);
    List<ProductImage> getProductImagesByProductIdAndImageType(String productId, ImageType type);
}
