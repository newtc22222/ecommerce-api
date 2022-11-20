package com.hcmute.ecom.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * @version 1.1
 * */
public class Product {
    private String id;
    private String name;
    private Long brand_id;
    private Long category_id;
    private LocalDate released_date;
    private Integer quantity_in_stock;
    private BigDecimal listed_price;
    private BigDecimal price;

    // Laptop's extra information
    private Byte ram_capacity;
    private String cpu_brand;
    private String cpu_type;
    private String cpu_more_infor_html;
    private String more_description_html;
    private Long screen_id;

    public Product(String id, String name, Long brand_id, Long category_id, LocalDate released_date,
                   Integer quantity_in_stock, BigDecimal listed_price, BigDecimal price, Byte ram_capacity,
                   String cpu_brand, String cpu_type, String cpu_more_infor_html, String more_description_html,
                   Long screen_id) {
        this.id = id;
        this.name = name;
        this.brand_id = brand_id;
        this.category_id = category_id;
        this.released_date = released_date;
        this.quantity_in_stock = quantity_in_stock;
        this.listed_price = listed_price;
        this.price = price;
        this.ram_capacity = ram_capacity;
        this.cpu_brand = cpu_brand;
        this.cpu_type = cpu_type;
        this.cpu_more_infor_html = cpu_more_infor_html;
        this.more_description_html = more_description_html;
        this.screen_id = screen_id;
    }

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

    public LocalDate getReleasedDate() {
        return released_date;
    }

    public void setReleasedDate(LocalDate released_date) {
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

    public Long getScreenId() {
        return screen_id;
    }

    public void setScreenId(Long screen_id) {
        this.screen_id = screen_id;
    }

    public Byte getRamCapacity() {
        return ram_capacity;
    }

    public void setRamCapacity(Byte ram_capacity) {
        this.ram_capacity = ram_capacity;
    }

    public String getCpuBrand() {
        return cpu_brand;
    }

    public void setCpuBrand(String cpu_brand) {
        this.cpu_brand = cpu_brand;
    }

    public String getCpuType() {
        return cpu_type;
    }

    public void setCpuType(String cpu_type) {
        this.cpu_type = cpu_type;
    }

    public String getCpuMoreInformationHTML() {
        return cpu_more_infor_html;
    }

    public void setCpuMoreInformationHTML(String cpu_more_infor_html) {
        this.cpu_more_infor_html = cpu_more_infor_html;
    }

    public String getMoreDescriptionHTML() {
        return more_description_html;
    }

    public void setMoreDescriptionHTML(String more_description_html) {
        this.more_description_html = more_description_html;
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
                ", screen_id=" + screen_id +
                ", ram_capacity=" + ram_capacity +
                ", cpu_brand='" + cpu_brand + '\'' +
                ", cpu_type='" + cpu_type + '\'' +
                ", cpu_more_infor_html='" + cpu_more_infor_html + '\'' +
                ", more_description_html='" + more_description_html + '\'' +
                '}';
    }
}
