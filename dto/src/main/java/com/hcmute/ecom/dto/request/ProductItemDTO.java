package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.model.ProductItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author Nhat Phi
 * @since 10-12-2022
 * */
@ApiModel("Class representing for ProductItem request body")
public class ProductItemDTO {
    @ApiModelProperty("This is need when user store their wanted item")
    private String cartId;
    @ApiModelProperty("This is need when user make a bill")
    private String invoiceId;
    @ApiModelProperty(required = true)
    private String productId;
    @ApiModelProperty(required = true)
    private Integer quantity;
    @ApiModelProperty(required = true)
    private BigDecimal price;
    @ApiModelProperty(required = true)
    private BigDecimal discountPrice;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public static ProductItem transform(ProductItemDTO itemDTO) {
        ProductItem item = new ProductItem();
        if(itemDTO.getCartId() != null) {
            item.setCartId(itemDTO.getCartId());
        }
        if(itemDTO.getInvoiceId() != null) {
            item.setInvoiceId(itemDTO.getInvoiceId());
        }
        item.setProductId(itemDTO.getProductId());
        item.setItemQuantity(itemDTO.getQuantity());
        item.setItemPrice(itemDTO.getPrice());
        item.setItemDiscountPrice(itemDTO.getDiscountPrice());
        return item;
    }
}
