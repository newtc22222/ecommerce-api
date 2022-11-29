package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.GraphicCardDAO;
import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.model.laptop.GraphicCard;
import com.hcmute.ecom.service.GraphicCardService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Service
public class GraphicCardServiceImpl implements GraphicCardService {
    @Autowired
    private GraphicCardDAO graphicCardDAO;

    @Override
    public ResponseEntity<?> insert(GraphicCard graphicCard) {
        return ResponseCUDObject.of(
                graphicCardDAO.insert(graphicCard) > 0,
                HttpStatus.CREATED,
                "Insert new graphic card successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new graphic card! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(GraphicCard graphicCard, long graphicCardId) {
        GraphicCard oldGraphicCard = graphicCardDAO.findGraphicCardById(graphicCardId);

        if(oldGraphicCard == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find graphic card with id = " + graphicCardId
                    ));
        }
        else {
            oldGraphicCard.setType(graphicCard.getType());
            oldGraphicCard.setBrand(graphicCard.getBrand());
            oldGraphicCard.setModel(graphicCard.getModel());
            oldGraphicCard.setMaxClockSpeed(graphicCard.getMaxClockSpeed());
            oldGraphicCard.setMemory(graphicCard.getMemory());
        }

        return ResponseCUDObject.of(
                graphicCardDAO.update(oldGraphicCard) > 0,
                HttpStatus.OK,
                "Update graphic card's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update graphic card! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long graphicCardId) {
        if(graphicCardDAO.findGraphicCardById(graphicCardId) == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find graphic card with id = " + graphicCardId
                    ));
        }
        return ResponseCUDObject.of(
                graphicCardDAO.delete(graphicCardId) > 0,
                HttpStatus.OK,
                "Delete graphic card successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot delete graphic card with id = " + graphicCardId
        );
    }

    @Override
    public ResponseEntity<?> getAllGraphicCards() {
        List<GraphicCard> graphicCardList = graphicCardDAO.getAllGraphicCards();
        if (graphicCardList == null || graphicCardList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any graphic card!"
                    ));
        }
        return ResponseEntity.ok(graphicCardList);
    }

    @Override
    public ResponseEntity<?> findGraphicCardById(long graphicCardId) {
        GraphicCard graphicCard = graphicCardDAO.findGraphicCardById(graphicCardId);
        if (graphicCard == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find graphic card with id = " + graphicCardId
                    ));
        }
        return ResponseEntity.ok(graphicCard);
    }

    @Override
    public ResponseEntity<?> getGraphicCardsByType(GraphicCardType type) {
        List<GraphicCard> graphicCardList = graphicCardDAO.getGraphicCardsByType(type);
        if (graphicCardList == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any graphic card which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(graphicCardList);
    }

    @Override
    public ResponseEntity<?> getGraphicCardsByBrand(String brand) {
        List<GraphicCard> graphicCardList = graphicCardDAO.getGraphicCardsByBrand(brand);
        if (graphicCardList == null || graphicCardList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any graphic card which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(graphicCardList);
    }

    @Override
    public ResponseEntity<?> getGraphicCardsByTypeAndBrand(GraphicCardType type, String brand) {
        List<GraphicCard> graphicCardList = graphicCardDAO.getGraphicCardsByTypeAndBrand(type, brand);
        if (graphicCardList == null || graphicCardList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any graphic card which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(graphicCardList);
    }
}
