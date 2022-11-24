package com.hcmute.ecom.controller;

import com.hcmute.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAllUsers() {
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
}
