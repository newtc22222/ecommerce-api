package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.AccountDTO;
import com.hcmute.ecom.service.ManagerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO account) {
        return managerAccountService.findAccount(account);
    }


}
