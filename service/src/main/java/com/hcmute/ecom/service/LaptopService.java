package com.hcmute.ecom.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-28
 * */
public interface LaptopService {
    ResponseEntity<?> getLaptopDetail(String laptopId);
    ResponseEntity<?> getLaptopCard(String laptopId);
    ResponseEntity<?> getLaptopCards();
    ResponseEntity<?> insertGraphicCard(String laptopId, long graphicCardId);
    ResponseEntity<?> deleteGraphicCard(String laptopId, long graphicCardId);
    ResponseEntity<?> insertHardDrive(String laptopId, long hardDriveId);
    ResponseEntity<?> deleteHardDrive(String laptopId, long hardDriveId);
    ResponseEntity<?> getLaptopCardsFilter(Map<String, Object> params);
}
