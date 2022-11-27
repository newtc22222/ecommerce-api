package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.FeedbackDAO;
import com.hcmute.ecom.model.Feedback;
import com.hcmute.ecom.service.FeedbackService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDAO feedbackDAO;

    @Override
    public ResponseEntity<?> insert(Feedback feedback) {
        return ResponseCUDObject.of(
                feedbackDAO.insert(feedback) > 0,
                HttpStatus.CREATED,
                "Insert new feedback successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new feedback! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(Feedback feedback, long feedbackId) {
        Feedback oldFeedback = feedbackDAO.findFeedbackById(feedbackId);

        if(oldFeedback == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find feedback with id = " + feedbackId
                    ));
        }
        else {
            oldFeedback.setContent(feedback.getContent());
            oldFeedback.setCreatedDate(feedback.getCreatedDate());
            oldFeedback.setRatingPoint(feedback.getRatingPoint());
        }

        return ResponseCUDObject.of(
                feedbackDAO.update(oldFeedback) > 0,
                HttpStatus.OK,
                "Update feedback's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update feedback! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long feedbackId) {
        Feedback feedback = feedbackDAO.findFeedbackById(feedbackId);
        if (feedback == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find feedback with id = " + feedbackId
                    ));
        }

        return ResponseCUDObject.of(
                feedbackDAO.delete(feedbackId, feedback.getProductId(), feedback.getUserId()) > 0,
                HttpStatus.OK,
                "Delete feedback successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to remove this feedback!"
        );
    }

    @Override
    public ResponseEntity<?> getAllFeedbacksOfProduct(String productId) {
        List<Feedback> feedbacks = feedbackDAO.getAllFeedbacksOfProduct(productId);
        if(feedbacks == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any feedback in this product!"
                    ));
        }
        return ResponseEntity.ok(feedbacks);
    }

    @Override
    public ResponseEntity<?> getAllFeedbacksOfProductByRatingPoint(String productId, byte ratingPoint) {
        List<Feedback> feedbacks = feedbackDAO.getAllFeedbacksOfProductByRatingPoint(productId, ratingPoint);
        if(feedbacks == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any feedback in this product of this point!"
                    ));
        }
        return ResponseEntity.ok(feedbacks);
    }

    @Override
    public ResponseEntity<?> getAllFeedbacksOfUser(long userId) {
        List<Feedback> feedbackList = feedbackDAO.getAllFeedbacksOfUser(userId);

        if(feedbackList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any feedback of user with id = " + userId
                    ));
        }

        return ResponseEntity.ok(feedbackList);
    }

    @Override
    public ResponseEntity<?> getNewFeedbacks(int limit) {
        List<Feedback> feedbackList = feedbackDAO.getNewFeedbacks(limit);

        if(feedbackList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any feedback!"
                    ));
        }

        return ResponseEntity.ok(feedbackList);
    }

    @Override
    public ResponseEntity<?> getAllFeedbacksByRatingPoint(byte ratingPoint) {
        List<Feedback> feedbackList = feedbackDAO.getAllFeedbacksByRatingPoint(ratingPoint);

        if(feedbackList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any feedback which suit this condition!"
                    ));
        }

        return ResponseEntity.ok(feedbackList);
    }

    @Override
    public ResponseEntity<?> findFeedbackById(long feedbackId) {
        Feedback feedback = feedbackDAO.findFeedbackById(feedbackId);

        if(feedback == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find feedback with id = " + feedbackId));
        }
        return ResponseEntity.ok(feedback);
    }
}
