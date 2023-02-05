package com.hcmute.ecom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmute.ecom.enums.Gender;
import com.hcmute.ecom.enums.Role;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private Date date_of_birth;
    private String phone;
    private String email;
    @JsonIgnore
    private String password;
    private Boolean isActive;
    private Role role;
    private LocalDateTime created_date;
    private LocalDateTime last_updated_date;

    public User(Long id, String name, Gender gender, Date date_of_birth, String phone, String email,
                String password, Boolean isActive, Role role, LocalDateTime created_date, LocalDateTime last_updated_date) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
        this.created_date = created_date;
        this.last_updated_date = last_updated_date;
    }

    public User() {
        this.id = 0L;
        this.gender = Gender.MALE;
        this.isActive = true;
        this.role = Role.USER;
        this.created_date = LocalDateTime.now();
        this.last_updated_date = LocalDateTime.now();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        this.isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id)
                && name.equals(user.name)
                && gender == user.gender
                && date_of_birth.equals(user.date_of_birth)
                && phone.equals(user.phone)
                && Objects.equals(email, user.email)
                && password.equals(user.password)
                && isActive.equals(user.isActive)
                && role == user.role
                && created_date.equals(user.created_date)
                && last_updated_date.equals(user.last_updated_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, date_of_birth, phone, email, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", date_of_birth=" + date_of_birth +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", role=" + role +
                ", created_date=" + created_date +
                ", last_updated_date=" + last_updated_date +
                '}';
    }
}
