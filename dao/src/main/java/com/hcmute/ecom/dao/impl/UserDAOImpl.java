package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.UserDAO;
import com.hcmute.ecom.dto.request.UserDTORequest;
import com.hcmute.ecom.enums.Gender;
import com.hcmute.ecom.mapper.UserMapper;
import com.hcmute.ecom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Component
public class UserDAOImpl implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_user";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE_ALL = String.format("update %s " +
            "set name=?, gender=?, phone=?, email=?, date_of_birth=?, created_date=?, last_updated_date=? where id=?", TABLE_NAME);
    private final String UPDATE_INFORMATION = String.format("update %s " +
            "set name=?, gender=?, email=?, date_of_birth=?, last_updated_date=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);
    private final String QUERY_ONE_BY_PHONE = String.format("select * from %s where phone=? limit 1", TABLE_NAME);
    private final String QUERY_USERS_BY_NAME = String.format("select * from %s where name like ?", TABLE_NAME);
    private final String QUERY_USERS_BY_GENDER = String.format("select * from %s where gender=?", TABLE_NAME);

    @Override
    public int insert(User user) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    user.getName(),
                    user.getGender().toString(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getDateOfBirth(),
                    Timestamp.valueOf(user.getCreatedDate()),
                    Timestamp.valueOf(user.getLastUpdatedDate())
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int updateAll(User user) {
        try {
            return jdbcTemplate.update(
                    UPDATE_ALL,
                    user.getName(),
                    user.getGender().toString(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getDateOfBirth(),
                    Timestamp.valueOf(user.getCreatedDate()),
                    Timestamp.valueOf(user.getLastUpdatedDate()),
                    user.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int updateInformation(UserDTORequest userDTO) {
        try {
            return jdbcTemplate.update(
                    UPDATE_INFORMATION,
                    userDTO.getName(),
                    userDTO.getGender().toString(),
                    userDTO.getEmail(),
                    userDTO.getDateOfBirth(),
                    Timestamp.valueOf(userDTO.getLastUpdatedDate()),
                    userDTO.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long userId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    userId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new UserMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public User findUserById(long userId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new UserMapper(),
                    userId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public User findUserByPhone(String phone) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_PHONE,
                    new UserMapper(),
                    phone
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<User> findUsersByName(String name) {
        try {
            return jdbcTemplate.query(
                    QUERY_USERS_BY_NAME,
                    new UserMapper(),
                    "%" + name + "%"
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByGender(Gender gender) {
        try {
            return jdbcTemplate.query(
                    QUERY_USERS_BY_GENDER,
                    new UserMapper(),
                    gender.toString()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
