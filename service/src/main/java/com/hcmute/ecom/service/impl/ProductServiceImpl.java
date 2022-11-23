package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dto.request.LaptopDTORequest;
import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.Product;
import com.hcmute.ecom.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public ResponseEntity<?> insert(Product product) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateAll(Product product) {
        return null;
    }

    @Override
    public ResponseEntity<?> updatePrice(ProductDTORequest productDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateLaptopProperties(LaptopDTORequest laptopDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(String productId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllProduct() {
        return null;
    }

    @Override
    public ResponseEntity<?> findProductById(String productId) {
        return null;
    }

    @Override
    public ResponseEntity<?> findProductsByConditions(Object... args) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllCommentsOfProduct(String productId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getDiscountsByProduct(String productId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllFeedbacksOfProduct(long productId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllFeedbacksOfProductByRatingPoint(String productId, byte ratingPoint) {
        return null;
    }

    @Override
    public ResponseEntity<?> getProductImagesByProductId(String productId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getProductImagesByProductIdAndImageType(String productId, ImageType type) {
        return null;
    }
}
