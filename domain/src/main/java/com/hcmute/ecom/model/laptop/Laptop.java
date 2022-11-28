package com.hcmute.ecom.model.laptop;

import com.hcmute.ecom.model.Product;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * @author Nhat Phi
 * @since 2022-11-28
 * */
public class Laptop extends Product {
    private Byte ram_capacity;
    private String cpu_brand;
    private String cpu_type;
    private String cpu_more_infor_html;
    private String more_description_html;
    private Long screen_id;

    public Laptop(String id, String name, Long brand_id, Long category_id, Date released_date,
                  Integer quantity_in_stock, BigDecimal listed_price, BigDecimal price, Byte ram_capacity,
                  String cpu_brand, String cpu_type, String cpu_more_infor_html,
                  String more_description_html, Long screen_id) {
        super(id, name, brand_id, category_id, released_date, quantity_in_stock, listed_price, price);
        this.ram_capacity = ram_capacity;
        this.cpu_brand = cpu_brand;
        this.cpu_type = cpu_type;
        this.cpu_more_infor_html = cpu_more_infor_html;
        this.more_description_html = more_description_html;
        this.screen_id = screen_id;
    }

    public Laptop() {}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return super.equals(o)
                && ram_capacity.equals(laptop.ram_capacity)
                && cpu_brand.equals(laptop.cpu_brand)
                && cpu_type.equals(laptop.cpu_type)
                && cpu_more_infor_html.equals(laptop.cpu_more_infor_html)
                && more_description_html.equals(laptop.more_description_html)
                && screen_id.equals(laptop.screen_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(screen_id);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Laptop{" +
                "ram_capacity=" + ram_capacity +
                ", cpu_brand='" + cpu_brand + '\'' +
                ", cpu_type='" + cpu_type + '\'' +
                ", cpu_more_infor_html='" + cpu_more_infor_html + '\'' +
                ", more_description_html='" + more_description_html + '\'' +
                ", screen_id=" + screen_id +
                "} " + super.toString();
    }
}
