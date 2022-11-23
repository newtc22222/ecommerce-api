package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.model.laptop.GraphicCard;
import com.hcmute.ecom.service.GraphicCardService;
import org.springframework.http.ResponseEntity;

public class GraphicCardServiceImpl implements GraphicCardService {
    @Override
    public ResponseEntity<?> insert(GraphicCard card) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(GraphicCard card) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long graphicCardId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllGraphicCards() {
        return null;
    }

    @Override
    public ResponseEntity<?> findGraphicCardById(long graphicCardId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getGraphicCardsByType(GraphicCardType type) {
        return null;
    }

    @Override
    public ResponseEntity<?> getGraphicCardsByBrand(String brand) {
        return null;
    }

    @Override
    public ResponseEntity<?> getGraphicCardsByTypeAndBrand(GraphicCardType type, String brand) {
        return null;
    }
}
