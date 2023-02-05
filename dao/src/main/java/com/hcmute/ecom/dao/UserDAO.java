package com.hcmute.ecom.dao;

import com.hcmute.ecom.dto.request.UserDTORequest;
import com.hcmute.ecom.enums.Gender;
import com.hcmute.ecom.enums.Role;
import com.hcmute.ecom.model.User;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface UserDAO {
    int insert(User user);
    int updateAll(User user);
    int updateInformation(UserDTORequest userDTO);
    int blockUser(long userId);

    List<User> getAllUsers();
    User findUserById(long userId);
    User findUserByPhone(String phone);
    List<User> findUsersByName(String name);
    List<User> getUsersByGender(Gender gender);
    List<User> getUserByRole(Role role);
}
