package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.ProductImage;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-26
 * */
public class ProductImageDTO {
    public static ProductImage transform(Map<String, String> request) {
        ProductImage image = new ProductImage();
        if (request.containsKey("id")) {
            image.setId(request.get("id"));
        }
        image.setProductId(request.get("productId"));
        if (request.containsKey("feedbackId")) {
            image.setFeedbackId(Long.getLong(request.get("feedbackId")));
        }
        image.setPath(request.get("path"));
        image.setImageType(ImageType.valueOf(request.get("type")));
        return image;
    }
}
