package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.FeedbackDAO;
import com.hcmute.ecom.mapper.FeedbackMapper;
import com.hcmute.ecom.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
@Component
public class FeedbackDAOImpl implements FeedbackDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_feedback";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set content=?, created_date=?, rating_point=? where id=? and product_id=? and user_id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=? and product_id=? and user_id=?", TABLE_NAME);

    private final String QUERY_ALL_FEEDBACKS_OF_PRODUCT =
            String.format("select * from %s where product_id=?", TABLE_NAME);
    private final String QUERY_ALL_FEEDBACKS_OF_USER =
            String.format("select * from %s where user_id=?", TABLE_NAME);
    private final String QUERY_NEW_FEEDBACKS = String.format("select * from %s " +
            "order by created_date desc limit ?", TABLE_NAME);
    private final String QUERY_ALL_FEEDBACKS_BY_RATING_POINT =
            String.format("select * from %s where rating_point=?", TABLE_NAME);
    private final String QUERY_ALL_FEEDBACKS_OF_PRODUCT_BY_RATING_POINT =
            String.format("select * from %s where product_id=? and rating_point=?", TABLE_NAME);
    private final String QUERY_ONE_BY_ID =
            String.format("select * from %s where id=?", TABLE_NAME);
    
    @Override
    public int insert(Feedback feedback) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    feedback.getProductId(),
                    feedback.getUserId(),
                    feedback.getContent(),
                    Timestamp.valueOf(feedback.getCreatedDate()),
                    feedback.getRatingPoint()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(Feedback feedback) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    feedback.getContent(),
                    Timestamp.valueOf(feedback.getCreatedDate()),
                    feedback.getRatingPoint(),
                    feedback.getId(),
                    feedback.getProductId(),
                    feedback.getUserId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long feedbackId, String productId, long userId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    feedbackId,
                    productId,
                    userId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<Feedback> getAllFeedbacksOfProduct(String productId) {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL_FEEDBACKS_OF_PRODUCT,
                    new FeedbackMapper(),
                    productId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Feedback> getAllFeedbacksOfUser(long userId) {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL_FEEDBACKS_OF_USER,
                    new FeedbackMapper(),
                    userId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Feedback> getNewFeedbacks(int limit) {
        try {
            return jdbcTemplate.query(
                    QUERY_NEW_FEEDBACKS,
                    new FeedbackMapper(),
                    limit
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Feedback> getAllFeedbacksByRatingPoint(byte ratingPoint) {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL_FEEDBACKS_BY_RATING_POINT,
                    new FeedbackMapper(),
                    ratingPoint
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Feedback> getAllFeedbacksOfProductByRatingPoint(String productId, byte ratingPoint) {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL_FEEDBACKS_OF_PRODUCT_BY_RATING_POINT,
                    new FeedbackMapper(),
                    productId,
                    ratingPoint
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Feedback findFeedbackById(long feedbackId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new FeedbackMapper(),
                    feedbackId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
