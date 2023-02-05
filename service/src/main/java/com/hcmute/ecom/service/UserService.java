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
    ResponseEntity<?> updateAll(User user, long userId);
    ResponseEntity<?> updateInformation(UserDTORequest userDTO, long userId);
    ResponseEntity<?> delete(long userId); // Maybe remove

    ResponseEntity<?> getAllUsers();
//    ResponseEntity<?> validateUser();

    @Deprecated
    ResponseEntity<?> findUserById(long userId);
    @Deprecated
    ResponseEntity<?> findUserByPhone(String phone);
    ResponseEntity<?> filter(String name, String gender, String role);
}
