package com.hcmute.ecom.model.laptop;

import com.hcmute.ecom.enums.product.HardDriveType;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class HardDrive {
    private Long id;
    private HardDriveType type;
    private String model;
    private Long capacity;
    private String standard;

    public HardDrive(Long id, HardDriveType type, String model, Long capacity, String standard) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.capacity = capacity;
        this.standard = standard;
    }

    public HardDrive() {
        this.id = 0L;
        this.type = HardDriveType.SSD;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HardDriveType getType() {
        return type;
    }

    public void setType(HardDriveType type) {
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

    @Override
    public String toString() {
        return "HardDrive{" +
                "id=" + id +
                ", type=" + type +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", standard='" + standard + '\'' +
                '}';
    }
}
