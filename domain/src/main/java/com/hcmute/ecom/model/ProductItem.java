package com.hcmute.ecom.model;

import java.math.BigDecimal;

/**
 * @author Nhat Phi
 * @since 2022-11-20
 * */
public class ProductItem {
    private String id;
    private String card_id;
    private String invoice_id;
    private String product_id;
    private Integer item_quantity;
    private BigDecimal item_price;
    private BigDecimal item_discount_price;

    public ProductItem(String id, String card_id, String invoice_id, String product_id, Integer item_quantity,
                       BigDecimal item_price, BigDecimal item_discount_price) {
        this.id = id;
        this.card_id = card_id;
        this.invoice_id = invoice_id;
        this.product_id = product_id;
        this.item_quantity = item_quantity;
        this.item_price = item_price;
        this.item_discount_price = item_discount_price;
    }

    public ProductItem() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardId() {
        return card_id;
    }

    public void setCardId(String card_id) {
        this.card_id = card_id;
    }

    public String getInvoiceId() {
        return invoice_id;
    }

    public void setInvoiceId(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
    }

    public Integer getItemQuantity() {
        return item_quantity;
    }

    public void setItemQuantity(Integer item_quantity) {
        this.item_quantity = item_quantity;
    }

    public BigDecimal getItemPrice() {
        return item_price;
    }

    public void setItemPrice(BigDecimal item_price) {
        this.item_price = item_price;
    }

    public BigDecimal getItemDiscountPrice() {
        return item_discount_price;
    }

    public void setItemDiscountPrice(BigDecimal item_discount_price) {
        this.item_discount_price = item_discount_price;
    }
}
