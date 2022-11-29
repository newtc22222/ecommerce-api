package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.Feedback;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class FeedbackMapper implements RowMapper<Feedback> {
    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getLong("id"));
        feedback.setProductId(rs.getString("product_id"));
        feedback.setUserId(rs.getLong("user_id"));
        feedback.setContent(rs.getNString("content"));
        feedback.setCreatedDate(LocalDateTime.parse(rs.getString("created_date"), DATE_TIME_PATTERN));
        feedback.setRatingPoint(rs.getByte("rating_point"));
        return feedback;
    }
}
