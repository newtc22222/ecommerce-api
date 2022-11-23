package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.ScreenDAO;
import com.hcmute.ecom.mapper.laptop.ScreenMapper;
import com.hcmute.ecom.model.laptop.Screen;
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
public class ScreenDAOImpl implements ScreenDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_screen";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set size=?, technology=?, resolution=?, type=?, refresh_rate=?, panel=?, brightness=?, " +
            "color_coverage=?, has_touch_screen=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);
    private final String QUERY_SCREENS_BY_SIZE = String.format("select * from %s where size=?", TABLE_NAME);
    private final String QUERY_SCREENS_BY_RESOLUTION = String.format("select * from %s where resolution=?", TABLE_NAME);
    private final String QUERY_SCREENS_BY_TYPE = String.format("select * from %s where type=?", TABLE_NAME);
    private final String QUERY_SCREENS_BY_PANEL = String.format("select * from %s where panel=?", TABLE_NAME);
    private final String QUERY_SCREENS_BY_TOUCH_SCREEN_TYPE = String.format("select * from %s where has_touch_screen=?", TABLE_NAME);

    @Override
    public int insert(Screen screen) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    screen.getSize(),
                    screen.getTechnology(),
                    screen.getResolution(),
                    screen.getType(),
                    screen.getRefreshRate(),
                    screen.getPanel(),
                    screen.getBrightness(),
                    screen.getColorCoverage(),
                    screen.getHasTouchScreen()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(Screen screen) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    screen.getSize(),
                    screen.getTechnology(),
                    screen.getResolution(),
                    screen.getType(),
                    screen.getRefreshRate(),
                    screen.getPanel(),
                    screen.getBrightness(),
                    screen.getColorCoverage(),
                    screen.getHasTouchScreen(),
                    screen.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long screenId) {
        try {
           return jdbcTemplate.update(
                   DELETE,
                   screenId
           );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<Screen> getAllScreens() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new ScreenMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Screen findScreenById(long screenId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new ScreenMapper(),
                    screenId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Screen> getScreensBySize(float size) {
        try {
            return jdbcTemplate.query(
                    QUERY_SCREENS_BY_SIZE,
                    new ScreenMapper(),
                    size
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Screen> getScreensByResolution(String resolution) {
        try {
            return jdbcTemplate.query(
                    QUERY_SCREENS_BY_RESOLUTION,
                    new ScreenMapper(),
                    resolution
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Screen> getScreensByType(String type) {
        try {
            return jdbcTemplate.query(
                    QUERY_SCREENS_BY_TYPE,
                    new ScreenMapper(),
                    type
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Screen> getScreensByPanel(String panel) {
        try {
            return jdbcTemplate.query(
                    QUERY_SCREENS_BY_PANEL,
                    new ScreenMapper(),
                    panel
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Screen> getScreensByTouchScreenType(boolean hasTouchScreen) {
        try {
            return jdbcTemplate.query(
                    QUERY_SCREENS_BY_TOUCH_SCREEN_TYPE,
                    new ScreenMapper(),
                    hasTouchScreen
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
