package com.hcmute.ecom.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * @version 1.2
 * */
public class Product {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrandId() {
        return brand_id;
    }

    public void setBrandId(Long brand_id) {
        this.brand_id = brand_id;
    }

    public Long getCategoryId() {
        return category_id;
    }

    public void setCategoryId(Long category_id) {
        this.category_id = category_id;
    }

    public Date getReleasedDate() {
        return released_date;
    }

    public void setReleasedDate(Date released_date) {
        this.released_date = released_date;
    }

    public Integer getQuantityInStock() {
        return quantity_in_stock;
    }

    public void setQuantityInStock(Integer quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public BigDecimal getListedPrice() {
        return listed_price;
    }

    public void setListedPrice(BigDecimal listed_price) {
        this.listed_price = listed_price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private String id;
    private String name;
    private Long brand_id;
    private Long category_id;
    private Date released_date;
    private Integer quantity_in_stock;
    private BigDecimal listed_price;
    private BigDecimal price;

    public Product(String id, String name, Long brand_id, Long category_id, Date released_date,
                   Integer quantity_in_stock, BigDecimal listed_price, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.brand_id = brand_id;
        this.category_id = category_id;
        this.released_date = released_date;
        this.quantity_in_stock = quantity_in_stock;
        this.listed_price = listed_price;
        this.price = price;
    }

    public Product() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id)
                && name.equals(product.name)
                && brand_id.equals(product.brand_id)
                && category_id.equals(product.category_id)
                && released_date.equals(product.released_date)
                && quantity_in_stock.equals(product.quantity_in_stock)
                && listed_price.equals(product.listed_price)
                && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand_id, category_id, listed_price, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand_id=" + brand_id +
                ", category_id=" + category_id +
                ", released_date=" + released_date +
                ", quantity_in_stock=" + quantity_in_stock +
                ", listed_price=" + listed_price +
                ", price=" + price +
                '}';
    }
}
