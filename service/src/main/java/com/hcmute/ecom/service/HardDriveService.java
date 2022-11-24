package com.hcmute.ecom.service;

import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.laptop.HardDrive;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface HardDriveService {
    ResponseEntity<?> insert(HardDrive hardDrive);
    ResponseEntity<?> update(HardDrive hardDrive, long hardDriveId);
    ResponseEntity<?> delete(long hardDriveId);
    ResponseEntity<?> getAllHardDrive();
    ResponseEntity<?> findHardDriveById(long hardDriveId);
    ResponseEntity<?> getHardDrivesByType(HardDriveType type);
}
