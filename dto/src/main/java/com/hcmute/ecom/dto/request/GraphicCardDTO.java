package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.model.laptop.GraphicCard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nhat Phi
 * @since 2022-11-28
 * */
@ApiModel("Class representing for Graphic card request body")
public class GraphicCardDTO {
    @ApiModelProperty(required = true, example = "ON_BOARD | DISCRETE")
    private String type;
    @ApiModelProperty(required = true)
    private String brand;
    @ApiModelProperty(required = true)
    private String model;
    @ApiModelProperty(required = true)
    private Integer maxClockSpeed;
    @ApiModelProperty(required = true)
    private String memory;

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
        return maxClockSpeed;
    }

    public void setMaxClockSpeed(Integer maxClockSpeed) {
        this.maxClockSpeed = maxClockSpeed;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public static GraphicCard transform(GraphicCardDTO graphicCardDTO) {
        GraphicCard card = new GraphicCard();
        try {
            card.setType(GraphicCardType.valueOf(graphicCardDTO.getType()));
        }
        catch (IllegalArgumentException err) {
            throw new RuntimeException(err);
        }
        card.setBrand(graphicCardDTO.getBrand());
        card.setModel(graphicCardDTO.getModel());
        card.setMaxClockSpeed(graphicCardDTO.getMaxClockSpeed());
        card.setMemory(graphicCardDTO.getMemory());
        return card;
    }
}
