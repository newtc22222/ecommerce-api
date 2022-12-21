package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.FeedbackDTO;
import com.hcmute.ecom.model.Feedback;
import com.hcmute.ecom.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Api(tags = "Feedback of Product", value = "Feedback controller")
@CrossOrigin(value = {"*"})
@RestController
@RequestMapping("/api/v1/")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @ApiOperation(value = "Get all feedbacks (with limit)", response = Feedback.class)
    @GetMapping("/new-feedbacks")
    public ResponseEntity<?> getNewFeedbacks(@RequestParam(value = "limit", defaultValue = "10") int limit) {
        return feedbackService.getNewFeedbacks(limit);
    }

    @ApiOperation(value = "Get one feedback with id", response = Feedback.class)
    @GetMapping("/feedbacks/{id}")
    public ResponseEntity<?> getFeedbackById(@PathVariable("id") long feedbackId) {
        return feedbackService.findFeedbackById(feedbackId);
    }

    @ApiOperation(value = "Get all feedbacks of a product", response = Feedback.class)
    @GetMapping("/products/{productId}/feedbacks")
    public ResponseEntity<?> getFeedbacksOfProduct(@PathVariable("productId") String productId,
                                                   @RequestParam(value = "point", required = false) Byte ratingPoint) {
        if (ratingPoint != null) {
            return feedbackService.getAllFeedbacksOfProductByRatingPoint(productId, ratingPoint);
        }
        return feedbackService.getAllFeedbacksOfProduct(productId);
    }

//    @CrossOrigin(value = { "https://localhost:3001" })
    @ApiOperation(value = "Get all feedbacks of user with userId", response = Feedback.class)
    @GetMapping("/users/{userId}/feedbacks")
    public ResponseEntity<?> getFeedbacksOfUser(@PathVariable("userId") long userId) {
        return feedbackService.getAllFeedbacksOfUser(userId);
    }

    @ApiOperation(value = "Create a new feedback", response = ResponseEntity.class)
    @PostMapping("/feedbacks")
    public ResponseEntity<?> createNewFeedback(@RequestBody Map<String, String> feedbackRequest) {
        return feedbackService.insert(FeedbackDTO.transform(feedbackRequest));
    }

    @ApiOperation(value = "Update a feedback", response = ResponseEntity.class)
    @PutMapping("/feedbacks/{id}")
    public ResponseEntity<?> updateFeedback(@PathVariable("id") long feedbackId,
                                            @RequestBody Map<String, String> feedbackRequest) {
        return feedbackService.update(FeedbackDTO.transform(feedbackRequest), feedbackId);
    }

    @ApiOperation(value = "Delete a feedback", response = ResponseEntity.class)
    @DeleteMapping("/feedbacks/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable("id") long feedbackId) {
        return feedbackService.delete(feedbackId);
    }
}
