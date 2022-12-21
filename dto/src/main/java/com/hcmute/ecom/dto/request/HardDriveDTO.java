package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.laptop.HardDrive;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nhat Phi
 * @since 2022-11-28
 * */
@ApiModel("Class representing for HardDrive request body")
public class HardDriveDTO {
    @ApiModelProperty(required = true, example = "HDD | SSD")
    private String type;
    @ApiModelProperty(required = true)
    private String model;
    @ApiModelProperty(required = true)
    private Long capacity;
    @ApiModelProperty(required = true)
    private String standard;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public static HardDrive transform(HardDriveDTO hardDriveDTO) {
        HardDrive drive = new HardDrive();
        try {
            drive.setType(HardDriveType.valueOf(hardDriveDTO.getType()));
        }
        catch (IllegalArgumentException err) {
            throw new RuntimeException(err);
        }
        drive.setModel(hardDriveDTO.getModel());
        drive.setCapacity(hardDriveDTO.getCapacity());
        drive.setStandard(hardDriveDTO.getStandard());
        return drive;
    }
}
