package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.Banner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class BannerMapper implements RowMapper<Banner> {
    @Override
    public Banner mapRow(ResultSet rs, int rowNum) throws SQLException {
        Banner banner = new Banner();
        banner.setId(rs.getLong("_id"));
        banner.setPath(rs.getString("path"));
        banner.setType(rs.getString("type"));
        banner.setUsed_date(rs.getDate("used_date"));
        banner.setEnded_date(rs.getDate("ended_date"));
        return banner;
    }
}
