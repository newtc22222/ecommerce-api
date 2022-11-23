package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.ManagerAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class ManagerAccountMapper implements RowMapper<ManagerAccount> {
    @Override
    public ManagerAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        ManagerAccount account = new ManagerAccount();
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        account.setCreatedDate(LocalDateTime.parse(rs.getString("created_date")));
        account.setLastUpdatedDate(LocalDateTime.parse(rs.getString("last_updated_date")));
        return account;
    }
}
