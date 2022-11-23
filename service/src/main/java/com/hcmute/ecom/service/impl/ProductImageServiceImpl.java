package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.ProductImage;
import com.hcmute.ecom.service.ProductImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Override
    public ResponseEntity<?> insert(ProductImage image) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(ProductImage image) {
        return null;
    }

    @Override
    public ResponseEntity<?> updatePathAndType(String imageId, String path, ImageType type) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String imageId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllProductImages() {
        return null;
    }

    @Override
    public ResponseEntity<?> findProductImageById(String imageId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getProductImagesByImageType(ImageType type) {
        return null;
    }
}
