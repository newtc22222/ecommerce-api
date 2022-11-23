package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.ManagerAccountDAO;
import com.hcmute.ecom.mapper.ManagerAccountMapper;
import com.hcmute.ecom.model.ManagerAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
@Component
public class ManagerAccountDAOImpl implements ManagerAccountDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_manager_account";
    private final String INSERT = String.format("insert into %s values (?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set password=?, created_date=?, last_updated_date=? where username=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where username=?", TABLE_NAME);

//    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ACCOUNT =
        String.format("select * from %s where username=? and password=? limit 1", TABLE_NAME);

    @Override
    public int insert(ManagerAccount account) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    account.getUsername(),
                    account.getPassword(),
                    Timestamp.valueOf(account.getCreatedDate()),
                    Timestamp.valueOf(account.getLastUpdatedDate())
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(ManagerAccount account) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    account.getPassword(),
                    Timestamp.valueOf(account.getCreatedDate()),
                    Timestamp.valueOf(account.getLastUpdatedDate()),
                    account.getUsername()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(String username) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    username
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public ManagerAccount findAccount(String username, String password) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ACCOUNT,
                    new ManagerAccountMapper(),
                    username,
                    password
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
