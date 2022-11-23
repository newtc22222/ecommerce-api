package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.Feedback;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface FeedbackDAO {
    int insert(Feedback feedback);
    int update(Feedback feedback);
    int delete(long feedbackId, String productId, long userId);
    List<Feedback> getAllFeedbacksOfProduct(String productId);
    List<Feedback> getAllFeedbacksOfUser(long userId);
    List<Feedback> getNewFeedbacks(int limit);
    List<Feedback> getAllFeedbacksByRatingPoint(byte ratingPoint);
    List<Feedback> getAllFeedbacksOfProductByRatingPoint(String productId, byte ratingPoint);
    Feedback findFeedbackById(long feedbackId);
}
