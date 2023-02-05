package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.HardDriveDAO;
import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.exception.NotFoundException;
import com.hcmute.ecom.model.laptop.HardDrive;
import com.hcmute.ecom.service.HardDriveService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class HardDriveServiceImpl implements HardDriveService {
    @Autowired
    private HardDriveDAO hardDriveDAO;

    @Override
    public ResponseEntity<?> insert(HardDrive hardDrive) {
        return ResponseCUDObject.of(
                hardDriveDAO.insert(hardDrive) > 0,
                HttpStatus.CREATED,
                "Insert new hard drive successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new hard drive! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(HardDrive hardDrive, long hardDriveId) {
        HardDrive oldHardDrive = hardDriveDAO.findHardDriveById(hardDriveId);
        if(oldHardDrive == null){
            throw new NotFoundException("Cannot find hard drive with id = " + hardDriveId);
        }
        else {
            oldHardDrive.setType(hardDrive.getType());
            oldHardDrive.setModel(hardDrive.getModel());
            oldHardDrive.setCapacity(hardDrive.getCapacity());
            oldHardDrive.setStandard(hardDrive.getStandard());
        }
        return ResponseCUDObject.of(
                hardDriveDAO.update(oldHardDrive) > 0,
                HttpStatus.OK,
                "Update hard drive's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update hard drive! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long hardDriveId) {
        if(hardDriveDAO.findHardDriveById(hardDriveId) == null){
            throw new NotFoundException("Cannot find hard drive with id = " + hardDriveId);
        }
        return ResponseCUDObject.of(
                hardDriveDAO.delete(hardDriveId) > 0,
                HttpStatus.OK,
                "Delete hard drive successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot delete hard drive with id = " + hardDriveId
        );
    }

    @Override
    public ResponseEntity<?> getAllHardDrive() {
        List<HardDrive> hardDriveList = hardDriveDAO.getAllHardDrive();
        if (hardDriveList == null || hardDriveList.size() == 0) {
            throw new NotFoundException("Can not find any hard drive in system!");
        }
        return ResponseEntity.ok(hardDriveList);
    }

    @Override
    public ResponseEntity<?> findHardDriveById(long hardDriveId) {
        HardDrive hardDrive = hardDriveDAO.findHardDriveById(hardDriveId);
        if (hardDrive == null) {
            throw new NotFoundException("Cannot find hard drive with id = " + hardDriveId);
        }
        return ResponseEntity.ok(hardDrive);
    }

    @Override
    public ResponseEntity<?> getHardDrivesByType(HardDriveType type) {
        List<HardDrive> hardDriveList = hardDriveDAO.getHardDrivesByType(type);
        if (hardDriveList == null || hardDriveList.size() == 0) {
            throw new NotFoundException("Cannot find any hard drive which suit this condition!");
        }
        return ResponseEntity.ok(hardDriveList);
    }
}
