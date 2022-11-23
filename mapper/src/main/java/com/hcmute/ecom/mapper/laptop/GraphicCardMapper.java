package com.hcmute.ecom.mapper.laptop;

import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.model.laptop.GraphicCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class GraphicCardMapper implements RowMapper<GraphicCard> {
    @Override
    public GraphicCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        GraphicCard card = new GraphicCard();
        card.setId(rs.getLong("id"));
        card.setType(GraphicCardType.valueOf(rs.getString("type")));
        card.setBrand(rs.getNString("brand"));
        card.setModel(rs.getString("model"));
        card.setMaxClockSpeed(rs.getInt("max_clock_speed"));
        card.setMemory(rs.getString("memory"));
        return card;
    }
}
