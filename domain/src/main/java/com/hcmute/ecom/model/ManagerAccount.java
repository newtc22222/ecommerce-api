package com.hcmute.ecom.model;

import java.time.LocalDateTime;

/**
 * This class is used for Admin and Manager's account
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class ManagerAccount {
    private String username;
    private String password;
    private LocalDateTime created_date;
    private LocalDateTime last_updated_date;

    public ManagerAccount(String username, String password, LocalDateTime created_date, LocalDateTime last_updated_date) {
        this.username = username;
        this.password = password;
        this.created_date = created_date;
        this.last_updated_date = last_updated_date;
    }
    
    public ManagerAccount() {
        this.created_date = LocalDateTime.now();
        this.last_updated_date = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDateTime getLastUpdatedDate() {
        return last_updated_date;
    }

    public void setLastUpdatedDate(LocalDateTime last_updated_date) {
        this.last_updated_date = last_updated_date;
    }
}
