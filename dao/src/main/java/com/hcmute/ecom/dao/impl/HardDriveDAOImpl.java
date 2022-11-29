package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.HardDriveDAO;
import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.mapper.laptop.HardDriveMapper;
import com.hcmute.ecom.model.laptop.HardDrive;
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
public class HardDriveDAOImpl implements HardDriveDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_hard_drive";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set type=?, model=?, capacity=?, standard=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);
    private final String QUERY_HARD_DRIVES_BY_PRODUCT_ID =
            String.format("select hd.* " +
                    "from %s hd, tbl_product_hard_drive phd " +
                    "where hd.id = phd.hard_drive_id and phd.product_id = ?", TABLE_NAME);
    private final String QUERY_HARD_DRIVES_BY_TYPE = String.format("select * from %s where type=?", TABLE_NAME);

    @Override
    public int insert(HardDrive hardDrive) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    hardDrive.getType().toString(),
                    hardDrive.getModel(),
                    hardDrive.getCapacity(),
                    hardDrive.getStandard()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(HardDrive hardDrive) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    hardDrive.getType().toString(),
                    hardDrive.getModel(),
                    hardDrive.getCapacity(),
                    hardDrive.getStandard(),
                    hardDrive.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long hardDriveId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    hardDriveId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<HardDrive> getAllHardDrive() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new HardDriveMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public HardDrive findHardDriveById(long hardDriveId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new HardDriveMapper(),
                    hardDriveId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<HardDrive> getHardDriveByProductId(String productId) {
        try {
            return jdbcTemplate.query(
                    QUERY_HARD_DRIVES_BY_PRODUCT_ID,
                    new HardDriveMapper(),
                    productId
            );
        }
        catch (Exception err) {
            return null;
        }
    }

    @Override
    public List<HardDrive> getHardDrivesByType(HardDriveType type) {
        try {
            return jdbcTemplate.query(
                    QUERY_HARD_DRIVES_BY_TYPE,
                    new HardDriveMapper(),
                    type.toString()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
