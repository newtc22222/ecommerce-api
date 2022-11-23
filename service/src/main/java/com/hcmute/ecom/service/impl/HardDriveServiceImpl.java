package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.laptop.HardDrive;
import com.hcmute.ecom.service.HardDriveService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class HardDriveServiceImpl implements HardDriveService {
    @Override
    public ResponseEntity<?> insert(HardDrive hardDrive) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(HardDrive hardDrive) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long hardDriveId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllHardDrive() {
        return null;
    }

    @Override
    public ResponseEntity<?> findHardDriveById(long hardDriveId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getHardDrivesByType(HardDriveType type) {
        return null;
    }
}
