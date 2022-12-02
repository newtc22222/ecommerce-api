package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.AccountDTO;
import com.hcmute.ecom.service.ManagerAccountService;
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
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/admin")
public class ManagerController {
    @Autowired
    private ManagerAccountService managerAccountService;

    @ApiOperation("Manager login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO account) {
        return managerAccountService.findAccount(account);
    }

    @ApiIgnore
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        return managerAccountService.insert(null);
    }
}
