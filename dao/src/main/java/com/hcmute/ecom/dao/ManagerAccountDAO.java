package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.ManagerAccount;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface ManagerAccountDAO {
    int insert(ManagerAccount account);
    int update(ManagerAccount account);
    int delete(String username);
    ManagerAccount findAccount(String username, String password);
}
