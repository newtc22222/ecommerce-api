package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dto.request.UserDTORequest;
import com.hcmute.ecom.model.User;
import com.hcmute.ecom.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResponseEntity<?> insert(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateAll(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateInformation(UserDTORequest userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        return null;
    }

    @Override
    public ResponseEntity<?> findUserById(long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> findUserByConditions(Object... args) {
        return null;
    }

    @Override
    public ResponseEntity<?> findCartByUserId(long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getInvoicesByUserId(long userId) {
        return null;
    }
}
