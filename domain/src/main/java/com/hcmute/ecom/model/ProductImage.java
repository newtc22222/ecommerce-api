package com.hcmute.ecom.model;

import com.hcmute.ecom.enums.ImageType;

import java.util.UUID;

/**
 * This class is a blueprint for the image of product <br/>
 * Image will save as path
 * 0 or 1 Feedback's image/product
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class ProductImage {
    private String id;
    private String product_id;
    private Long feedback_id;
    private String path;
    private ImageType type;

    public ProductImage(String id, String product_id, Long feedback_id, String path, ImageType type) {
        this.id = id;
        this.product_id = product_id;
        this.feedback_id = feedback_id;
        this.path = path;
        this.type = type;
    }

    public ProductImage() {
        this.id = UUID.randomUUID().toString();
        this.type = ImageType.ADVERTISE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
    }

    public Long getFeedbackId() {
        return feedback_id;
    }

    public void setFeedbackId(Long feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ImageType getImageType() {
        return type;
    }

    public void setImageType(ImageType type) {
        this.type = type;
    }
}
