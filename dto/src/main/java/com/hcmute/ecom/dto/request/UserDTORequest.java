package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.Gender;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class UserDTORequest {
    private Long id;
    private String name;
    private Gender gender;
    private String email;
    private Date date_of_birth;
    private LocalDateTime last_updated_date;

    public UserDTORequest(Long id, String name, Gender gender, String email, Date date_of_birth, LocalDateTime last_updated_date) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.last_updated_date = last_updated_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public LocalDateTime getLastUpdatedDate() {
        return last_updated_date;
    }

    public void setLastUpdatedDate(LocalDateTime last_updated_date) {
        this.last_updated_date = last_updated_date;
    }
}
