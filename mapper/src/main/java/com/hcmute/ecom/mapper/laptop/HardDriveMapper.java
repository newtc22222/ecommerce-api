package com.hcmute.ecom.mapper.laptop;

import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.laptop.HardDrive;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class HardDriveMapper implements RowMapper<HardDrive> {
    @Override
    public HardDrive mapRow(ResultSet rs, int rowNum) throws SQLException {
        HardDrive hardDrive = new HardDrive();
        hardDrive.setId(rs.getLong("id"));
        hardDrive.setType(HardDriveType.valueOf(rs.getString("type")));
        hardDrive.setModel(rs.getString("model"));
        hardDrive.setCapacity(rs.getLong("capacity"));
        hardDrive.setStandard(rs.getString("standard"));
        return hardDrive;
    }
}
