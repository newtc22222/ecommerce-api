package com.hcmute.ecom.service;

import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-28
 * */
public interface LaptopService {
    ResponseEntity<?> getLaptopDetail(String laptopId);
    ResponseEntity<?> insertGraphicCard(String laptopId, long graphicCardId);
    ResponseEntity<?> deleteGraphicCard(String laptopId, long graphicCardId);
    ResponseEntity<?> insertHardDrive(String laptopId, long hardDriveId);
    ResponseEntity<?> deleteHardDrive(String laptopId, long hardDriveId);
}
