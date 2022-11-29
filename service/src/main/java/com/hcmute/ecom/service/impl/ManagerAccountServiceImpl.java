package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.ManagerAccountDAO;
import com.hcmute.ecom.dto.request.AccountDTO;
import com.hcmute.ecom.model.ManagerAccount;
import com.hcmute.ecom.service.ManagerAccountService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Service
public class ManagerAccountServiceImpl implements ManagerAccountService {
    @Autowired
    private ManagerAccountDAO managerAccountDAO;

    @Override
    public ResponseEntity<?> insert(ManagerAccount account) {
        return ResponseCUDObject.of(
                managerAccountDAO.insert(account) > 0,
                HttpStatus.CREATED,
                "Insert new manager's account successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new manager's account! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(ManagerAccount account, String username) {
        ManagerAccount oldAccount = managerAccountDAO.findAccountByUsername(username);
        if(oldAccount == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find username = " + username
                    ));
        }
        else {
            oldAccount.setPassword(account.getPassword());
            oldAccount.setLastUpdatedDate(account.getLastUpdatedDate());
        }
        return ResponseCUDObject.of(
                managerAccountDAO.updatePassword(oldAccount) > 0,
                HttpStatus.OK,
                "Update account's password successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update account! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(String username) {
        if(managerAccountDAO.findAccountByUsername(username) == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find account with username = " + username
                    ));
        }
        return ResponseCUDObject.of(
                managerAccountDAO.delete(username) > 0,
                HttpStatus.OK,
                "Delete account successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot delete account with username = " + username
        );
    }

    @Override
    public ResponseEntity<?> findAccount(AccountDTO account) {
        ManagerAccount findAccount = managerAccountDAO.findAccount(account.getUsername(), account.getPassword());
        if(findAccount == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Username or password is not valid! Please check your account again!"
                    ));
        }
        return ResponseEntity.ok(findAccount);
    }
}
