package com.hcmute.ecom.dao;

import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.model.laptop.GraphicCard;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface GraphicCardDAO {
    int insert(GraphicCard card);
    int update(GraphicCard card);
    int delete(long cardId);
    List<GraphicCard> getAllGraphicCards();
    GraphicCard findGraphicCardById(long graphicCardId);
    List<GraphicCard> getGraphicCardsByType(GraphicCardType type);
    List<GraphicCard> getGraphicCardsByBrand(String brand);
    List<GraphicCard> getGraphicCardsByTypeAndBrand(GraphicCardType type, String brand);
}
