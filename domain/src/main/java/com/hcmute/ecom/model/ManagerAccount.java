package com.hcmute.ecom.model;

import java.time.LocalDate;

/**
 * This class is used for Admin and Manager's account
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class ManagerAccount {
    private String username;
    private String password;
    private LocalDate created_date;
    private LocalDate last_updated_date;

    public ManagerAccount(String username, String password, LocalDate created_date, LocalDate last_updated_date) {
        this.username = username;
        this.password = password;
        this.created_date = created_date;
        this.last_updated_date = last_updated_date;
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

    public LocalDate getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(LocalDate created_date) {
        this.created_date = created_date;
    }

    public LocalDate getLastUpdatedDate() {
        return last_updated_date;
    }

    public void setLastUpdatedDate(LocalDate last_updated_date) {
        this.last_updated_date = last_updated_date;
    }
}
