package com.hcmute.ecom.service;

import com.hcmute.ecom.model.Address;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface AddressService {
    ResponseEntity<?> insert(Address address);
    ResponseEntity<?> update(Address address, long addressId);
    ResponseEntity<?> delete(long addressId);
//    ResponseEntity<?> getAllAddress();
    ResponseEntity<?> findAddressById(long addressId);
    ResponseEntity<?> getAllAddressOfUser(long userId);
}
