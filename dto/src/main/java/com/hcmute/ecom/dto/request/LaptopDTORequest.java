package com.hcmute.ecom.dto.request;

import java.sql.Date;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
public class LaptopDTORequest {
    private String id;
    private String name;
    private Long brand_id;
    private Date released_date;

    // Laptop's extra information
    private Byte ram_capacity;
    private String cpu_brand;
    private String cpu_type;
    private String cpu_more_infor_html;
    private String more_description_html;
    private Long screen_id;

    public LaptopDTORequest(String id, String name, Long brand_id, Date released_date, Byte ram_capacity,
                            String cpu_brand, String cpu_type, String cpu_more_infor_html,
                            String more_description_html, Long screen_id) {
        this.id = id;
        this.name = name;
        this.brand_id = brand_id;
        this.released_date = released_date;
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

    public Date getReleasedDate() {
        return released_date;
    }

    public void setReleasedDate(Date released_date) {
        this.released_date = released_date;
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
}
