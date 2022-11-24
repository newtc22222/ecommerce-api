package com.hcmute.ecom.service;

import com.hcmute.ecom.dto.request.AccountDTO;
import com.hcmute.ecom.model.ManagerAccount;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface ManagerAccountService {
    ResponseEntity<?> insert(ManagerAccount account);
    ResponseEntity<?> update(ManagerAccount account, String username);
    ResponseEntity<?> delete(String username);
    ResponseEntity<?> findAccount(AccountDTO account);
}
