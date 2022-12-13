package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.Gender;
import com.hcmute.ecom.model.User;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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

    public UserDTORequest() {
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

    /**
     * update All information of User
     * */
    public static User transform(Map<String, String> request) {
        User user = new User();
        user.setName(request.get("name"));
        user.setGender(Gender.valueOf(request.get("gender")));
        user.setPhone(request.get("phone"));
        user.setEmail(request.get("email"));
        user.setDateOfBirth(Date.valueOf(request.get("dateOfBirth")));
        if(request.containsKey("createdDate")) {
            user.setCreatedDate(LocalDateTime.parse(request.get("createdDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        if(request.containsKey("lastUpdatedDate")) {
            user.setLastUpdatedDate(LocalDateTime.parse(request.get("lastUpdatedDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        return user;
    }

    /**
     * update some information of User
     * except Phone, created Date
     * */
    public static UserDTORequest getData(Map<String, String> request) {
        UserDTORequest user = new UserDTORequest();
        user.setName(request.get("name"));
        user.setGender(Gender.valueOf(request.get("gender")));
        user.setEmail(request.get("email"));
        try {
            user.setDateOfBirth(Date.valueOf(request.get("dateOfBirth")));
        }
        catch (Exception err) {
            throw new RuntimeException(err);
        }
        if(request.containsKey("lastUpdatedDate")) {
            user.setLastUpdatedDate(LocalDateTime.parse(request.get("lastUpdatedDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        return user;
    }
}
