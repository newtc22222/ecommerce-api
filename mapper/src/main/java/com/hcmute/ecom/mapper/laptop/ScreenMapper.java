package com.hcmute.ecom.mapper.laptop;

import com.hcmute.ecom.model.laptop.Screen;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class ScreenMapper implements RowMapper<Screen> {
    @Override
    public Screen mapRow(ResultSet rs, int rowNum) throws SQLException {
        Screen screen = new Screen();
        screen.setId(rs.getLong("id"));
        screen.setSize(rs.getFloat("size"));
        screen.setTechnology(rs.getNString("technology"));
        screen.setResolution(rs.getString("resolution"));
        screen.setType(rs.getString("type"));
        screen.setRefreshRate(rs.getFloat("refresh_rate"));
        screen.setPanel(rs.getString("panel"));
        screen.setBrightness(rs.getInt("brightness"));
        screen.setColorCoverage(rs.getString("color_coverage"));
        screen.setHasTouchScreen(rs.getBoolean("has_touch_screen"));
        return screen;
    }
}
