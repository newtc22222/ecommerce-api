package com.hcmute.ecom.controller.jwt;

import com.hcmute.ecom.service.jwt.JwtService;
import com.hcmute.ecom.service.jwt.dto.JwtRequest;
import com.hcmute.ecom.service.jwt.dto.JwtResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "JWT Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class JwtController {
    @Autowired
    private JwtService jwtService;

    @ApiOperation(value = "Handle login for an user", response = JwtResponse.class)
    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> createJwtToken(@RequestBody JwtRequest jwtRequest) {
        if(jwtRequest.getPhone().startsWith("0")) {
            jwtRequest.setPhone(jwtRequest.getPhone().replaceAll("^.", "+84"));
        }
        return ResponseEntity.ok(jwtService.createJwtToken(jwtRequest));
    }
}
