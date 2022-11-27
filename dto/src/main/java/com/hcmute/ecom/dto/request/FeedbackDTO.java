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
    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Feedback transform(Map<String, String> request) {
        Feedback feedback = new Feedback();
        feedback.setProductId(request.get("productId"));
        feedback.setUserId(Long.getLong(request.get("userId")));
        feedback.setContent(request.get("content"));
        feedback.setCreatedDate(LocalDateTime.parse(request.get("createdDate"), DATE_TIME_PATTERN));
        feedback.setRatingPoint(Byte.valueOf(request.get("ratingPoint")));
        return feedback;
    }
}
