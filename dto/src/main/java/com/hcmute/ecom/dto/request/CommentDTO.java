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
    public static Comment transform(Map<String, String> request) {
        Comment comment = new Comment();
        if(request.containsKey("rootCommentId")){
            comment.setRootCommentId(Long.parseLong(request.get("rootCommentId")));
        }
        comment.setProductId(request.get("productId"));
        comment.setUsername(request.get("username"));
        comment.setPhone(request.get("phone"));
        comment.setContent(request.get("content"));

        if(request.containsKey("createdDate")) {
            comment.setCreatedDate(LocalDateTime.parse(request.get("createdDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        return comment;
    }
}
