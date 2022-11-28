package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.UserDAO;
import com.hcmute.ecom.dto.request.UserDTORequest;
import com.hcmute.ecom.enums.Gender;
import com.hcmute.ecom.model.User;
import com.hcmute.ecom.service.UserService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public ResponseEntity<?> insert(User user) {
        return ResponseCUDObject.of(
                userDAO.insert(user) > 0,
                HttpStatus.CREATED,
                "Create new user successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to create new user! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updateAll(User user, long userId) {
        User oldUser = userDAO.findUserById(userId);
        if(oldUser == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find user with id = " + userId
                    ));
        }
        else {
            oldUser.setName(user.getName());
            oldUser.setGender(user.getGender());
            oldUser.setPhone(user.getPhone());
            oldUser.setEmail(user.getEmail());
            oldUser.setDateOfBirth(user.getDateOfBirth());
            //oldUser.setCreatedDate(user.getCreatedDate());
            oldUser.setLastUpdatedDate(user.getLastUpdatedDate());
        }
        return ResponseCUDObject.of(
                userDAO.updateAll(oldUser) > 0,
                HttpStatus.OK,
                "Update all information of user successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update user! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updateInformation(UserDTORequest userDTO, long userId) {
        User oldUser = userDAO.findUserById(userId);
        if(oldUser == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find user with id = " + userDTO.getId()
                    ));
        }


        userDTO.setId(userId);
        return ResponseCUDObject.of(
                userDAO.updateInformation(userDTO) > 0,
                HttpStatus.OK,
                "Update information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update user! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long userId) {
        if(userDAO.findUserById(userId) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find user with id = " + userId
                    ));
        }

        return ResponseCUDObject.of(
                userDAO.delete(userId) > 0,
                HttpStatus.CREATED,
                "Remove user successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to remove user!"
        );
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        if(users == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any user in system!"
                    ));
        }

        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<?> findUserById(long userId) {
        User user = userDAO.findUserById(userId);

        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find user with id = " + userId
                    ));
        }
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> findUserByPhone(String phone) {
        User user = userDAO.findUserByPhone(phone);

        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find user with phone = " + phone
                    ));
        }
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> filter(String name, String gender) {
        Set<User> userSet = new HashSet<>(userDAO.getAllUsers());
        Set<User> notSuit = new HashSet<>();
        if(name != null) {
            List<User> userList = userDAO.findUsersByName(name);
            userSet.forEach(user -> {
                if(!userList.contains(user)) {
                    notSuit.add(user);
                }
            });
        }
        if(gender != null) {
            List<User> userList = userDAO.getUsersByGender(Gender.valueOf(gender.toUpperCase()));
            userSet.forEach(user -> {
                if(!userList.contains(user)) {
                    notSuit.add(user);
                }
            });
        }

        userSet.removeAll(notSuit);
        if(userSet.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any user in this condition!"
                    ));
        }
        return ResponseEntity.ok(userSet);
    }
}
