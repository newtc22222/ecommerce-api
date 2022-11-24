package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.BannerDAO;
import com.hcmute.ecom.mapper.BannerMapper;
import com.hcmute.ecom.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
@Component
public class BannerDAOImpl implements BannerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_banner";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set path=?, type=?, used_date=?, end_date=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);
    // started_date - using_date(use-end) - ended_date
    private final String QUERY_ALL_BY_DATE_RANGE = String.format("select * from %s " +
            "where ended_date > ? or used_date < ?", TABLE_NAME);

    @Override
    public int insert(Banner banner) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    banner.getPath(),
                    banner.getType(),
                    banner.getUsedDate(),
                    banner.getEndedDate()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(Banner banner) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    banner.getPath(),
                    banner.getType(),
                    banner.getUsedDate(),
                    banner.getEndedDate(),
                    banner.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long bannerId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    bannerId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<Banner> getAllBanner() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new BannerMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Banner> getAllBannerByDateRange(Date start_date, Date ended_date) {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL_BY_DATE_RANGE,
                    new BannerMapper(),
                    start_date,
                    ended_date
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Banner findBannerById(long bannerId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new BannerMapper(),
                    bannerId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
