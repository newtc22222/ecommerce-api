package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.model.Address;
import com.hcmute.ecom.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public ResponseEntity<?> insert(Address address) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Address address) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long addressId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllAddress() {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllAddressOfUser(long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> findAddressById(long addressId) {
        return null;
    }
}
