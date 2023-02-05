package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.AccountDTO;
import com.hcmute.ecom.service.ManagerAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Api(tags = "Administrator account controller", value = "ManagerAccount controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/admin")
public class ManagerController {
    @Autowired
    private ManagerAccountService managerAccountService;

    @ApiOperation(value = "Login in system", response = ResponseEntity.class)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO account) {
        return managerAccountService.findAccount(account);
    }
}
