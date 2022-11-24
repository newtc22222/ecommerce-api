package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.AddressDAO;
import com.hcmute.ecom.model.Address;
import com.hcmute.ecom.service.AddressService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDAO addressDAO;

    @Override
    public ResponseEntity<?> insert(Address address) {
        return ResponseCUDObject.of(
                addressDAO.insert(address) > 0,
                HttpStatus.CREATED,
                "Insert new address successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new address! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(Address address, long addressId) {
        Address oldAddress = addressDAO.findAddressById(addressId);
        
        if(oldAddress == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find address with id = " + addressId
                    ));
        }
        else {  // change information
//            oldAddress.setUserId(address.getUserId());
            oldAddress.setCountry(address.getCountry());
            oldAddress.setLine1(address.getLine1());
            oldAddress.setLine2(address.getLine2());
            oldAddress.setLine3(address.getLine3());
            oldAddress.setStreet(address.getStreet());
        }
        
        return ResponseCUDObject.of(
                addressDAO.update(oldAddress) > 0,
                HttpStatus.OK,
                "Update address's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update address! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long addressId) {
        if(addressDAO.findAddressById(addressId) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find address with id = " + addressId
                    ));
        }

        return ResponseCUDObject.of(
                addressDAO.delete(addressId) > 0,
                HttpStatus.OK,
                "Delete address successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot remove this address!"
        );
    }

    @Override
    public ResponseEntity<?> getAllAddress() {
        List<Address> addresses = addressDAO.getAllAddress();
        if (addresses == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any address!"
                    ));
        }
        return ResponseEntity.ok(addresses);
    }

    @Override
    public ResponseEntity<?> findAddressById(long addressId) {
        Address address = addressDAO.findAddressById(addressId);
        if(address == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find address with id = " + addressId
                    ));
        }

        return ResponseEntity.ok(address);
    }

    @Override
    public ResponseEntity<?> getAllAddressOfUser(long userId) {
        List<Address> addressList = addressDAO.getAllAddressOfUser(userId);
        if(addressList == null || addressList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find any address of this user!"
                    ));
        }
        return ResponseEntity.ok(addressList);
    }
}
