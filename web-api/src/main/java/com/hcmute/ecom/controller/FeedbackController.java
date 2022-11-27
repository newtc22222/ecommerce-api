package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.FeedbackDTO;
import com.hcmute.ecom.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin(value = {"*"})
@RestController
@RequestMapping("/api/v1/")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/new-feedbacks")
    public ResponseEntity<?> getNewFeedbacks(@RequestParam(value = "limit", defaultValue = "10") int limit) {
        return feedbackService.getNewFeedbacks(limit);
    }

    // Get All Feedback
    // Get All Feedback By point

    @GetMapping("/feedbacks/{id}")
    public ResponseEntity<?> getFeedbackById(@PathVariable("id") long feedbackId) {
        return feedbackService.findFeedbackById(feedbackId);
    }

    @GetMapping("/products/{productId}/feedbacks")
    public ResponseEntity<?> getFeedbacksOfProduct(@PathVariable("productId") String productId,
                                                   @RequestParam(value = "point", required = false) Byte ratingPoint) {
        if (ratingPoint != null) {
            return feedbackService.getAllFeedbacksOfProductByRatingPoint(productId, ratingPoint);
        }
        return feedbackService.getAllFeedbacksOfProduct(productId);
    }

//    @CrossOrigin(value = { "https://localhost:3001" })
    @GetMapping("/users/{userId}/feedbacks")
    public ResponseEntity<?> getFeedbacksOfUser(@PathVariable("userId") long userId) {
        return feedbackService.getAllFeedbacksOfUser(userId);
    }

    @PostMapping("/feedbacks")
    public ResponseEntity<?> createNewFeedback(@RequestBody Map<String, String> feedbackRequest) {
        return feedbackService.insert(FeedbackDTO.transform(feedbackRequest));
    }

    @PutMapping("/feedbacks/{id}")
    public ResponseEntity<?> updateFeedback(@PathVariable("id") long feedbackId,
                                            @RequestBody Map<String, String> feedbackRequest) {
        return feedbackService.update(FeedbackDTO.transform(feedbackRequest), feedbackId);
    }

    @DeleteMapping("/feedbacks/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable("id") long feedbackId) {
        return feedbackService.delete(feedbackId);
    }
}
