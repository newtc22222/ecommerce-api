package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.model.laptop.GraphicCard;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-28
 * */
public class GraphicCardDTO {
    public static GraphicCard transform(Map<String, String> request) {
        GraphicCard card = new GraphicCard();
        card.setType(GraphicCardType.valueOf(request.get("type")));
        card.setBrand(request.get("brand"));
        card.setModel(request.get("model"));
        card.setMaxClockSpeed(Integer.getInteger(request.get("maxClockSpeed")));
        card.setMemory(request.get("memory"));
        return card;
    }
}
