package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.UserDTORequest;
import com.hcmute.ecom.model.User;
import com.hcmute.ecom.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Api(tags = "User information in system", value = "User controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get all users in system", response = User.class)
    @GetMapping("")
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "gender", required = false) String gender) {
        if(name != null || gender != null) {
            return userService.filter(name, gender);
        }

        return userService.getAllUsers();
    }

    @ApiOperation(value = "Handle login for an user", response = User.class)
    @GetMapping("/login")
    public ResponseEntity<?> findUserByPhone(@RequestParam(value = "phoneNumber", required = false) String phone) {
        if (phone.charAt(0) == '0') {
            phone = phone.replaceAll("^.", "+84");
        }
        return userService.findUserByPhone(phone);
    }

    @ApiOperation(value = "Get an user with id", response = User.class)
    @GetMapping("{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") long userId) {
        return userService.findUserById(userId);
    }

    @ApiOperation(value = "Create new user (register)", response = ResponseEntity.class)
    @PostMapping("")
    public ResponseEntity<?> createNewUser(@RequestBody Map<String, String> userRequest) {
        return userService.insert(UserDTORequest.transform(userRequest));
    }

    @ApiOperation(value = "Update all information of user", response = ResponseEntity.class)
    @PutMapping("{id}")
    public ResponseEntity<?> updateAllForUser(@PathVariable("id") long userId,
                                              @RequestBody Map<String, String> userRequest) {
        return userService.updateAll(UserDTORequest.transform(userRequest), userId);
    }

    @ApiOperation(value = "Update some information", response = ResponseEntity.class)
    @PatchMapping(value = "{id}", consumes = "application/json")
    public ResponseEntity<?> updateUserInformation(@PathVariable("id") long userId,
                                                   @RequestBody Map<String, String> userRequest) {

        return userService.updateInformation(UserDTORequest.getData(userRequest), userId);
    }

    @ApiIgnore
    @ApiOperation(value = "Remove user from system", response = ResponseEntity.class)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long userId) {
        return userService.delete(userId);
    }
}
