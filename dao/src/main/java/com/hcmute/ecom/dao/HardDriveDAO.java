package com.hcmute.ecom.dao;

import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.laptop.HardDrive;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface HardDriveDAO {
    int insert(HardDrive hardDrive);
    int update(HardDrive hardDrive);
    int delete(long hardDriveId);
    List<HardDrive> getAllHardDrive();
    HardDrive findHardDriveById(long hardDriveId);
    List<HardDrive> getHardDriveByProductId(String productId);
    List<HardDrive> getHardDrivesByType(HardDriveType type);
}
