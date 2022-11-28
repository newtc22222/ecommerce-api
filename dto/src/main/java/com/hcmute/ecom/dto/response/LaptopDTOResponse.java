package com.hcmute.ecom.dto.response;

import com.hcmute.ecom.model.Brand;
import com.hcmute.ecom.model.Category;
import com.hcmute.ecom.model.laptop.GraphicCard;
import com.hcmute.ecom.model.laptop.HardDrive;
import com.hcmute.ecom.model.laptop.Laptop;
import com.hcmute.ecom.model.laptop.Screen;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class LaptopDTOResponse {
    private String id;
    private String name;
    private Brand brand;
    private Category category;
    private Date released_date;
    private Integer quantity_in_stock;
    private BigDecimal listed_price;
    private BigDecimal price;

    // Laptop's extra information
    private Byte ram_capacity;
    private String cpu_brand;
    private String cpu_type;
    private String cpu_more_infor_html;
    private String more_description_html;
    private List<HardDrive> hardDriveList;
    private List<GraphicCard> graphicCardList;
    private Screen screen;

    public LaptopDTOResponse() {}

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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getCpuMoreInforHtml() {
        return cpu_more_infor_html;
    }

    public void setCpuMoreInforHtml(String cpu_more_infor_html) {
        this.cpu_more_infor_html = cpu_more_infor_html;
    }

    public String getMoreDescriptionHtml() {
        return more_description_html;
    }

    public void setMoreDescriptionHtml(String more_description_html) {
        this.more_description_html = more_description_html;
    }

    public List<HardDrive> getHardDriveList() {
        return hardDriveList;
    }

    public void setHardDriveList(List<HardDrive> hardDriveList) {
        this.hardDriveList = hardDriveList;
    }

    public List<GraphicCard> getGraphicCardList() {
        return graphicCardList;
    }

    public void setGraphicCardList(List<GraphicCard> graphicCardList) {
        this.graphicCardList = graphicCardList;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public static LaptopDTOResponse getData(Laptop product, Brand brand, Category category, List<GraphicCard> graphicCardList,
                                     List<HardDrive> hardDriveList, Screen screen) {
        LaptopDTOResponse laptop = new LaptopDTOResponse();
        laptop.setId(product.getId());
        laptop.setName(product.getName());
        laptop.setBrand(brand);
        laptop.setCategory(category);
        laptop.setReleasedDate(product.getReleasedDate());
        laptop.setQuantityInStock(product.getQuantityInStock());
        laptop.setListedPrice(product.getListedPrice());
        laptop.setPrice(product.getPrice());
        laptop.setRamCapacity(product.getRamCapacity());
        laptop.setCpuBrand(product.getCpuBrand());
        laptop.setCpuType(product.getCpuType());
        laptop.setCpuMoreInforHtml(product.getCpuMoreInformationHTML());
        laptop.setMoreDescriptionHtml(product.getMoreDescriptionHTML());
        laptop.setGraphicCardList(graphicCardList);
        laptop.setHardDriveList(hardDriveList);
        laptop.setScreen(screen);
        return laptop;
    }
}
