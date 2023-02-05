package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.UserDTORequest;
import com.hcmute.ecom.model.User;
import com.hcmute.ecom.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get all users in system", response = User.class)
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "gender", required = false) String gender,
                                         @RequestParam(value = "role", required = false) String role) {
        if(name != null || gender != null || role != null) {
            return userService.filter(name, gender, role);
        }

        return userService.getAllUsers();
    }

    @ApiOperation(value = "Get an user with id", response = User.class)
    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> findUserById(@PathVariable("id") long userId) {
        return userService.findUserById(userId);
    }

    @ApiOperation(value = "Create new user (register)", response = ResponseEntity.class)
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerNewUser(@RequestBody Map<String, String> userRequest) {
        return userService.insert(UserDTORequest.transform(userRequest));
    }

    @ApiOperation(value = "Update all information of user", response = ResponseEntity.class)
    @PutMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> updateAllForUser(@PathVariable("id") long userId,
                                              @RequestBody Map<String, String> userRequest) {
        return userService.updateAll(UserDTORequest.transform(userRequest), userId);
    }

    @ApiOperation(value = "Update some information", response = ResponseEntity.class)
    @PatchMapping(value = "{id}", consumes = "application/json")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> updateUserInformation(@PathVariable("id") long userId,
                                                   @RequestBody Map<String, String> userRequest) {

        return userService.updateInformation(UserDTORequest.getData(userRequest), userId);
    }

    @ApiIgnore
    @ApiOperation(value = "Disable user in system", response = ResponseEntity.class)
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> blockUser(@PathVariable("id") long userId) {
        return userService.delete(userId);
    }
}
