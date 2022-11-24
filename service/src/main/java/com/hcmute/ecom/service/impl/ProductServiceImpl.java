package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.*;
import com.hcmute.ecom.dto.request.LaptopDTORequest;
import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.*;
import com.hcmute.ecom.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private DiscountDAO discountDAO;

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private ProductImageDAO productImageDAO;

    @Override
    public ResponseEntity<?> insert(Product product) {
        return ResponseCUDObject.of(
            productDAO.insert(product) > 0,
            HttpStatus.CREATED,
            "Create new product successfully!",
            HttpStatus.NOT_IMPLEMENTED,
            "Failed to create new product! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updateAll(Product product, String productId) {
        Product oldProduct = productDAO.findProductById(productId);

        if(oldProduct == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + productId
                    ));
        }
        else {
            oldProduct.setName(product.getName());
            oldProduct.setBrandId(product.getBrandId());
            oldProduct.setCategoryId(product.getCategoryId());
            oldProduct.setReleasedDate(product.getReleasedDate());
            oldProduct.setQuantityInStock(product.getQuantityInStock());
            oldProduct.setListedPrice(product.getListedPrice());
            oldProduct.setPrice(product.getPrice());

            oldProduct.setRamCapacity(product.getRamCapacity());
            oldProduct.setCpuBrand(product.getCpuBrand());
            oldProduct.setCpuType(product.getCpuType());
            oldProduct.setCpuMoreInformationHTML(product.getCpuMoreInformationHTML());
            oldProduct.setMoreDescriptionHTML(product.getMoreDescriptionHTML());
            oldProduct.setScreenId(product.getScreenId());
        }

        return ResponseCUDObject.of(
                productDAO.updateAll(product) > 0,
                HttpStatus.OK,
                "Update product successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update product! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updatePrice(ProductDTORequest productDTO) {
        Product oldProduct = productDAO.findProductById(productDTO.getId());

        if(oldProduct == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + productDTO.getId()
                    ));
        }

        return ResponseCUDObject.of(
                productDAO.updatePrice(productDTO) > 0,
                HttpStatus.OK,
                "Update price of product successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update price of product! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updateLaptopProperties(LaptopDTORequest laptopDTO) {
        Product oldProduct = productDAO.findProductById(laptopDTO.getId());

        if(oldProduct == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + laptopDTO.getId()
                    ));
        }

        return ResponseCUDObject.of(
                productDAO.updateLaptopProperties(laptopDTO) > 0,
                HttpStatus.OK,
                "Update laptop's properties successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update laptop's properties! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(String productId) {
        if(productDAO.findProductById(productId) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + productId
                    ));
        }

        return ResponseCUDObject.of(
                productDAO.delete(productId) > 0,
                HttpStatus.OK,
                "Delete product successfully",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to delete this product!"
        );
    }

    @Override
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productDAO.getAllProduct();
        if(products == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any product!"
                    ));
        }
        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<?> findProductById(String productId) {
        Product product = productDAO.findProductById(productId);
        if(product == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + productId
                    ));
        }
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<?> findProductsByConditions(Object... args) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllCommentsOfProduct(String productId) {
        List<Comment> comments = commentDAO.getAllCommentsOfProduct(productId);
        if(comments == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any comment in this product!"
                    ));
        }
        return ResponseEntity.ok(comments);
    }

    @Override
    public ResponseEntity<?> getDiscountsByProduct(String productId) {
        List<Discount> discounts = discountDAO.getDiscountsByProduct(productId);
        if(discounts == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any discount in this product!"
                    ));
        }
        return ResponseEntity.ok(discounts);
    }

    @Override
    public ResponseEntity<?> getAllFeedbacksOfProduct(String productId) {
        List<Feedback> feedbacks = feedbackDAO.getAllFeedbacksOfProduct(productId);
        if(feedbacks == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any feedback in this product!"
                    ));
        }
        return ResponseEntity.ok(feedbacks);
    }

    @Override
    public ResponseEntity<?> getAllFeedbacksOfProductByRatingPoint(String productId, byte ratingPoint) {
        List<Feedback> feedbacks = feedbackDAO.getAllFeedbacksOfProductByRatingPoint(productId, ratingPoint);
        if(feedbacks == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any feedback in this product of this point!"
                    ));
        }
        return ResponseEntity.ok(feedbacks);
    }

    @Override
    public ResponseEntity<?> getProductImagesByProductId(String productId) {
        List<ProductImage> images = productImageDAO.getProductImagesByProductId(productId);
        if(images == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any images in this product!"
                    ));
        }
        return ResponseEntity.ok(images);
    }

    @Override
    public ResponseEntity<?> getProductImagesByProductIdAndImageType(String productId, ImageType type) {
        List<ProductImage> images = productImageDAO.getProductImagesByProductIdAndImageType(productId, type);
        if(images == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any images in this product of this type!"
                    ));
        }
        return ResponseEntity.ok(images);
    }
}
