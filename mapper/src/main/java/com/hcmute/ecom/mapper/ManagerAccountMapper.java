package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.ManagerAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class ManagerAccountMapper implements RowMapper<ManagerAccount> {
    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public ManagerAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        ManagerAccount account = new ManagerAccount();
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        account.setCreatedDate(LocalDateTime.parse(rs.getString("created_date"), DATE_TIME_PATTERN));
        account.setLastUpdatedDate(LocalDateTime.parse(rs.getString("last_updated_date"), DATE_TIME_PATTERN));
        return account;
    }
}
