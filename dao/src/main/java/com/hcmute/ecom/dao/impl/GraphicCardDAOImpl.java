package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.GraphicCardDAO;
import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.mapper.laptop.GraphicCardMapper;
import com.hcmute.ecom.model.laptop.GraphicCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Component
public class GraphicCardDAOImpl implements GraphicCardDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_graphic_card";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set type=?, brand=?, model=?, max_clock_speed=?, memory=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);
    private final String QUERY_GRAPHIC_CARDS_BY_PRODUCT_ID =
            String.format("select gc.* " +
                    "from %s gc, joshua_tbl_product_graphic_card pgc " +
                    "where gc._id = pgc.graphic_card_id and pgc.product_id = ?", TABLE_NAME);
    private final String QUERY_GRAPHIC_CARDS_BY_TYPE =
            String.format("select * from %s where type=?", TABLE_NAME);
    private final String QUERY_GRAPHIC_CARDS_BY_BRAND =
            String.format("select * from %s where brand like ?", TABLE_NAME);
    private final String QUERY_GRAPHIC_CARDS_BY_TYPE_AND_BRAND =
            String.format("select * from %s where type=? and brand like ?", TABLE_NAME);

    @Override
    public int insert(GraphicCard card) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    card.getType().toString(),
                    card.getBrand(),
                    card.getModel(),
                    card.getMaxClockSpeed(),
                    card.getMemory()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(GraphicCard card) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    card.getType().toString(),
                    card.getBrand(),
                    card.getModel(),
                    card.getMaxClockSpeed(),
                    card.getMemory(),
                    card.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long cardId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    cardId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<GraphicCard> getAllGraphicCards() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new GraphicCardMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public GraphicCard findGraphicCardById(long graphicCardId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new GraphicCardMapper(),
                    graphicCardId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<GraphicCard> getGraphicCardByProductId(String productId) {
        try {
            return jdbcTemplate.query(
                    QUERY_GRAPHIC_CARDS_BY_PRODUCT_ID,
                    new GraphicCardMapper(),
                    productId
            );
        }
        catch (Exception err) {
            return null;
        }
    }

    @Override
    public List<GraphicCard> getGraphicCardsByType(GraphicCardType type) {
        try {
            return jdbcTemplate.query(
                    QUERY_GRAPHIC_CARDS_BY_TYPE,
                    new GraphicCardMapper(),
                    type.toString()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<GraphicCard> getGraphicCardsByBrand(String brand) {
        try {
            return jdbcTemplate.query(
                    QUERY_GRAPHIC_CARDS_BY_BRAND,
                    new GraphicCardMapper(),
                    "%" + brand + "%"
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<GraphicCard> getGraphicCardsByTypeAndBrand(GraphicCardType type, String brand) {
        try {
            return jdbcTemplate.query(
                    QUERY_GRAPHIC_CARDS_BY_TYPE_AND_BRAND,
                    new GraphicCardMapper(),
                    type.toString(),
                    "%" + brand + "%"
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
