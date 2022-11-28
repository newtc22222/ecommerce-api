package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.model.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
public class CommentDTO {
    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Comment transform(Map<String, String> request) {
        Comment comment = new Comment();
        if(request.containsKey("rootCommentId")){
            comment.setRootCommentId(Long.getLong(request.get("rootCommentId")));
        }
        comment.setProductId(request.get("productId"));
        comment.setUsername(request.get("username"));
        comment.setPhone(request.get("phone"));
        comment.setContent(request.get("content"));

        if(request.containsKey("createdDate")) {
            comment.setCreatedDate(LocalDateTime.parse(request.get("createdDate"), DATE_TIME_PATTERN));
        }
        return comment;
    }
}
