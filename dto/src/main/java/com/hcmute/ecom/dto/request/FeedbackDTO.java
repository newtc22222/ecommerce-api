package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.model.Feedback;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
public class FeedbackDTO {
    private Feedback feedback;
    private String username;

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static FeedbackDTO getData(Feedback feedback, String username) {
        FeedbackDTO feedbackResponse = new FeedbackDTO();
        feedbackResponse.setFeedback(feedback);
        feedbackResponse.setUsername(username);
        return feedbackResponse;
    }

    public static Feedback transform(Map<String, String> request) {
        Feedback feedback = new Feedback();
        feedback.setProductId(request.get("productId"));
        feedback.setUserId(Long.parseLong(request.get("userId")));
        feedback.setContent(request.get("content"));
        if(request.get("createdDate") == null) {
            feedback.setCreatedDate(LocalDateTime.parse(request.get("createdDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        feedback.setRatingPoint(Byte.parseByte(request.get("ratingPoint")));
        return feedback;
    }
}
