package com.hcmute.ecom.controller;

import com.hcmute.ecom.model.Address;
import com.hcmute.ecom.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Api(tags = "Address Of User", value = "address CRUD and get address of User")
@CrossOrigin(value = {"*"})
@RestController
@RequestMapping("/api/v1")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Get address by addressId", response = Address.class)
    @GetMapping("/address/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable("id") long addressId) {
        return addressService.findAddressById(addressId);
    }

    @ApiOperation(value = "Get list address of user (userId)", response = Address.class)
    @GetMapping("/users/{userId}/address")
    public ResponseEntity<?> getAllAddressOfUser(@PathVariable("userId") long userId) {
        return addressService.getAllAddressOfUser(userId);
    }

    @ApiOperation(value = "Add new address information", response = ResponseEntity.class)
    @PostMapping("/address")
    public ResponseEntity<?> createNewAddress(@RequestBody Address address) {
        return addressService.insert(address);
    }

    @ApiOperation(value = "Update address information", response = ResponseEntity.class)
    @PutMapping("/address/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("id") long addressId, @RequestBody Address address) {
        return addressService.update(address, addressId);
    }

    @ApiOperation(value = "Remove address", response = ResponseEntity.class)
    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") long addressId) {
        return addressService.delete(addressId);
    }
}
