package com.hcmute.ecom.service;

import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.model.laptop.GraphicCard;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface GraphicCardService {
    ResponseEntity<?> insert(GraphicCard card);
    ResponseEntity<?> update(GraphicCard card);
    ResponseEntity<?> delete(long graphicCardId);
    ResponseEntity<?> getAllGraphicCards();
    ResponseEntity<?> findGraphicCardById(long graphicCardId);
    ResponseEntity<?> getGraphicCardsByType(GraphicCardType type);
    ResponseEntity<?> getGraphicCardsByBrand(String brand);
    ResponseEntity<?> getGraphicCardsByTypeAndBrand(GraphicCardType type, String brand);
}
