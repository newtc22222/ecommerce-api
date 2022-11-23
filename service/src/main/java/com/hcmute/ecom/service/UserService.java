package com.hcmute.ecom.service;

import com.hcmute.ecom.dto.request.UserDTORequest;
import com.hcmute.ecom.model.User;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface UserService {
    ResponseEntity<?> insert(User user);
    ResponseEntity<?> updateAll(User user);
    ResponseEntity<?> updateInformation(UserDTORequest userDTO);
    ResponseEntity<?> delete(long userId);

    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> findUserById(long userId);
    ResponseEntity<?> findUserByConditions(Object ...args);

//    ResponseEntity<?> findUserByPhone(String phone);
//    ResponseEntity<?> findUsersByName(String name);
//    ResponseEntity<?> getUsersByGender(Gender gender);

    // Cart
    ResponseEntity<?> findCartByUserId(long userId);
    // Invoice
    ResponseEntity<?> getInvoicesByUserId(long userId);
}
