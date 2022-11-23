package com.hcmute.ecom.model;

import com.hcmute.ecom.enums.Gender;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * <h3>User Model</h3>
 * This class is used for Customer <b>(they use phone to login)</b>
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class User {
    private Long id;
    private String name;
    private Gender gender;
    private String phone;
    private String email;
    private Date date_of_birth;
    private LocalDateTime created_date;
    private LocalDateTime last_updated_date;

    public User(Long id, String name, Gender gender, String phone, String email, Date date_of_birth, LocalDateTime created_date, LocalDateTime last_updated_date) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.created_date = created_date;
        this.last_updated_date = last_updated_date;
    }

    public User() {}

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", created_date=" + created_date +
                ", last_updated_date=" + last_updated_date +
                '}';
    }
}
