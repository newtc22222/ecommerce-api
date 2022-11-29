package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.laptop.HardDrive;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-28
 * */
public class HardDriveDTO {
    public static HardDrive transform(Map<String, String> request) {
        HardDrive drive = new HardDrive();
        try {
            drive.setType(HardDriveType.valueOf(request.get("type")));
        }
        catch (Exception err) {
            throw new RuntimeException(err);
        }
        finally {
            drive.setType(HardDriveType.SSD);
        }
        drive.setModel(request.get("model"));
        drive.setCapacity(Long.parseLong(request.get("capacity")));
        drive.setStandard(request.get("standard"));
        return drive;
    }
}
