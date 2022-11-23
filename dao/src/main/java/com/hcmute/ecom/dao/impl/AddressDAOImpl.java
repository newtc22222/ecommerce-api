package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.AddressDAO;
import com.hcmute.ecom.mapper.AddressMapper;
import com.hcmute.ecom.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-20
 * */
@Component
public class AddressDAOImpl implements AddressDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_address";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set user_id=?, country=?, line1=?, line2=?, line3=?, street=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=? and user_id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);
    private final String QUERY_ALL_BY_USER_ID = String.format("select * from %s where user_id=?", TABLE_NAME);

    @Override
    public int insert(Address address) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    address.getUserId(),
                    address.getCountry(),
                    address.getLine1(),
                    address.getLine2(),
                    address.getLine3(),
                    address.getStreet()
            );
        }
        catch (Exception err){
            return 0;
        }
    }

    @Override
    public int update(Address address) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    address.getUserId(),
                    address.getCountry(),
                    address.getLine1(),
                    address.getLine2(),
                    address.getLine3(),
                    address.getStreet(),
                    address.getId()
            );
        }
        catch (Exception err){
            return 0;
        }
    }

    @Override
    public int delete(long addressId) {
        try {
            return jdbcTemplate.update(DELETE, addressId);
        }
        catch (Exception err){
            return 0;
        }
    }

    @Override
    public List<Address> getAllAddress() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new AddressMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    /**
     * Get all address of 1 user (show detail)
     * */
    @Override
    public List<Address> getAllAddressOfUser(long userId) {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL_BY_USER_ID,
                    new AddressMapper(),
                    userId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Address findAddressById(long addressId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new AddressMapper(),
                    addressId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
