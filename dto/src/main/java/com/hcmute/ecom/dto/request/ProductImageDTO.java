package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.ProductImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-26
 * */
@ApiModel("Class representing for ProductImage request body")
public class ProductImageDTO {
    private String id;
    private Long feedbackId;
    @ApiModelProperty(required = true)
    private String productId;
    @ApiModelProperty(required = true)
    private String path;
    @ApiModelProperty(required = true)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ProductImage transform(ProductImageDTO productImageDTO) {
        ProductImage image = new ProductImage();
        if (productImageDTO.getId() != null) {
            image.setId(productImageDTO.getId());
        }
        if (productImageDTO.getFeedbackId() != null) {
            image.setFeedbackId(productImageDTO.getFeedbackId());
        }
        image.setProductId(productImageDTO.getProductId());
        image.setPath(productImageDTO.getPath());

        try {
            image.setImageType(ImageType.valueOf(productImageDTO.getType()));
        }
        catch (IllegalArgumentException err) {
            throw new RuntimeException(err);
        }

        return image;
    }
}
