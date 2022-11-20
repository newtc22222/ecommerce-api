package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.Address;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-20
 * */
public interface AddressDAO {
    int insert(Address address);
    int update(Address address);
    int delete(long addressId);
    List<Address> getAllAddress();
    List<Address> getAllAddressOfUser(long userId);
    Address findAddressById(long addressId);
}
