package com.hcmute.ecom.model.laptop;

import com.hcmute.ecom.enums.product.GraphicCardType;

/**
 * This class describe for the graphic technology
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class GraphicCard {
    private Long id;
    private GraphicCardType type;
    private String brand;
    private String model;
    private Integer max_clock_speed;
    private String memory;

    public GraphicCard(Long id, GraphicCardType type, String brand, String model, Integer max_clock_speed, String memory) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.max_clock_speed = max_clock_speed;
        this.memory = memory;
    }

    public GraphicCard() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GraphicCardType getType() {
        return type;
    }

    public void setType(GraphicCardType type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMaxClockSpeed() {
        return max_clock_speed;
    }

    public void setMaxClockSpeed(Integer max_clock_speed) {
        this.max_clock_speed = max_clock_speed;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "GraphicCard{" +
                "id=" + id +
                ", type=" + type +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", max_clock_speed=" + max_clock_speed +
                ", memory='" + memory + '\'' +
                '}';
    }
}
