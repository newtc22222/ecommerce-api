package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.ProductHardDriveDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Component
public class ProductHardDriveDAOImpl implements ProductHardDriveDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE_NAME = "tbl_product_hard_drive";
    private final String INSERT = String.format("insert into %s values (?, ?)", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where product_id=? and hard_drive_id=?", TABLE_NAME);

    @Override
    public int insert(String productId, long hardDriveId) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    productId,
                    hardDriveId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(String productId, long hardDriveId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    productId,
                    hardDriveId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }
}
