package com.hcmute.ecom.service;

import com.hcmute.ecom.model.Feedback;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface FeedbackService {
    ResponseEntity<?> insert(Feedback feedback);
    ResponseEntity<?> update(Feedback feedback, long feedbackId);
    ResponseEntity<?> delete(long feedbackId, String productId, long userId);
    ResponseEntity<?> getAllFeedbacksOfProduct(String productId);
    ResponseEntity<?> getAllFeedbacksOfProductByRatingPoint(String productId, byte ratingPoint);
    ResponseEntity<?> getAllFeedbacksOfUser(long userId);
    ResponseEntity<?> getNewFeedbacks(int limit);
    ResponseEntity<?> getAllFeedbacksByRatingPoint(byte ratingPoint);
    ResponseEntity<?> findFeedbackById(long feedbackId);
}
