package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.model.Feedback;
import com.hcmute.ecom.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Override
    public ResponseEntity<?> insert(Feedback feedback) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Feedback feedback) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long feedbackId, String productId, long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllFeedbacksOfUser(long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getNewFeedbacks(int limit) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllFeedbacksByRatingPoint(byte ratingPoint) {
        return null;
    }

    @Override
    public ResponseEntity<?> findFeedbackById(long feedbackId) {
        return null;
    }
}
