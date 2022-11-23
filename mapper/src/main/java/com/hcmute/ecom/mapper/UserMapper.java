package com.hcmute.ecom.mapper;

import com.hcmute.ecom.enums.Gender;
import com.hcmute.ecom.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getNString("name"));
        user.setGender(Gender.valueOf(rs.getString("gender")));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        user.setDateOfBirth(rs.getDate("date_of_birth"));
        user.setCreatedDate(LocalDateTime.parse(rs.getString("created_date")));
        user.setLastUpdatedDate(LocalDateTime.parse(rs.getString("last_updated_date")));
        return user;
    }
}
