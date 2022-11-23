package com.hcmute.ecom.service;

import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.ProductImage;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface ProductImageService {
    ResponseEntity<?> insert(ProductImage image);
    ResponseEntity<?> update(ProductImage image);
    ResponseEntity<?> updatePathAndType(String imageId, String path, ImageType type);
    ResponseEntity<?> delete(String imageId);
    ResponseEntity<?> getAllProductImages();
    ResponseEntity<?> findProductImageById(String imageId);
    ResponseEntity<?> getProductImagesByImageType(ImageType type);
}
