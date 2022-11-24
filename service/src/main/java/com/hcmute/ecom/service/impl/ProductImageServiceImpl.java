package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.ProductImageDAO;
import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.ProductImage;
import com.hcmute.ecom.service.ProductImageService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageDAO productImageDAO;

    @Override
    public ResponseEntity<?> insert(ProductImage productImage) {
        return ResponseCUDObject.of(
                productImageDAO.insert(productImage) > 0,
                HttpStatus.CREATED,
                "Insert new product image successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new product image! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(ProductImage productImage, String imageId) {
        ProductImage oldProductImage = productImageDAO.findProductImageById(imageId);

        if(oldProductImage == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product image with id = " + imageId
                    ));
        }
        else {
            oldProductImage.setFeedbackId(productImage.getFeedbackId());
            oldProductImage.setPath(productImage.getPath());
            oldProductImage.setImageType(productImage.getImageType());
        }

        return ResponseCUDObject.of(
                productImageDAO.update(oldProductImage) > 0,
                HttpStatus.OK,
                "Update product image's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update product image! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updatePathAndType(String imageId, String path, ImageType type) {
        ProductImage oldProductImage = productImageDAO.findProductImageById(imageId);

        if(oldProductImage == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product image with id = " + imageId
                    ));
        }

        return ResponseCUDObject.of(
                productImageDAO.updatePathAndType(imageId, path, type) > 0,
                HttpStatus.OK,
                "Update product image's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update product image! Please check your data again!"
        );
    }
    
    @Override
    public ResponseEntity<?> delete(String imageId) {
        return ResponseCUDObject.of(
                productImageDAO.delete(imageId) > 0,
                HttpStatus.OK,
                "Delete product image successfully!",
                HttpStatus.NOT_FOUND,
                "Cannot find product image with id = " + imageId
        );
    }


    @Override
    public ResponseEntity<?> getAllProductImages() {
        List<ProductImage> productImageList = productImageDAO.getAllProductImages();
        if(productImageList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any product image!"
                    ));
        }
        return ResponseEntity.ok(productImageList);
    }

    @Override
    public ResponseEntity<?> findProductImageById(String imageId) {
        ProductImage productImage = productImageDAO.findProductImageById(imageId);
        if(productImage == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product image with id = " + imageId
                    ));
        }
        return ResponseEntity.ok(productImage);
    }

    @Override
    public ResponseEntity<?> getProductImagesByImageType(ImageType type) {
        List<ProductImage> productImageList = productImageDAO.getProductImagesByImageType(type);
        if(productImageList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any product image which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(productImageList);
    }
}
