package com.hcmute.ecom.controller;

import com.hcmute.ecom.model.Address;
import com.hcmute.ecom.service.AddressService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/address/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable("id") long addressId) {
        return addressService.findAddressById(addressId);
    }

    @GetMapping("/users/{userId}/address")
    public ResponseEntity<?> getAllAddressOfUser(@PathVariable("userId") long userId) {
        return addressService.getAllAddressOfUser(userId);
    }

    @PostMapping("/address")
    public ResponseEntity<?> createNewAddress(@RequestBody Address address) {
        return addressService.insert(address);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("id") long addressId, @RequestBody Address address) {
        return addressService.update(address, addressId);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") long addressId) {
        return addressService.delete(addressId);
    }
}
