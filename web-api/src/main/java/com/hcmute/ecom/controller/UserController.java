package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.UserDTORequest;
import com.hcmute.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "gender", required = false) String gender) {
        if(name != null || gender != null) {
            return userService.filter(name, gender);
        }

        return userService.getAllUsers();
    }

    @GetMapping("/search")
    public ResponseEntity<?> findUserByPhone(@RequestParam(value = "phoneNumber", required = false) String phone) {
        if (phone.charAt(0) == '0') {
            phone = phone.replaceAll("^.", "+84");
        }
        return userService.findUserByPhone(phone);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewUser(@RequestBody Map<String, String> userRequest) {
        return userService.insert(UserDTORequest.transform(userRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAllForUser(@PathVariable("id") long userId,
                                              @RequestBody Map<String, String> userRequest) {
        return userService.updateAll(UserDTORequest.transform(userRequest), userId);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<?> updateUserInformation(@PathVariable("id") long userId,
                                                   @RequestBody Map<String, String> userRequest) {

        return userService.updateInformation(UserDTORequest.getData(userRequest), userId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long userId) {
        return userService.delete(userId);
    }
}
