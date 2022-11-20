package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.AddressDAO;
import com.hcmute.ecom.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-20
 * */
@Component
public class AddressDAOImpl implements AddressDAO {
    @Override
    public int insert(Address address) {
        return 0;
    }

    @Override
    public int update(Address address) {
        return 0;
    }

    @Override
    public int delete(long addressId) {
        return 0;
    }

    @Override
    public List<Address> getAllAddress() {
        return null;
    }

    /**
     * Get all address of 1 user (show detail)
     * */
    @Override
    public List<Address> getAllAddressOfUser(long userId) {
        return null;
    }

    @Override
    public Address findAddressById(long addressId) {
        return null;
    }
}
