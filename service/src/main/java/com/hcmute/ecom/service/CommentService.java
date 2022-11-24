package com.hcmute.ecom.service;

import com.hcmute.ecom.model.Comment;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface CommentService {
    ResponseEntity<?> insert(Comment comment);
    ResponseEntity<?> update(Comment comment, long commentId);
    ResponseEntity<?> delete(long commentId);
    ResponseEntity<?> getAllCommentsOfProduct(String productId);
    ResponseEntity<?> getAllCommentsOfUser(String phone);
    ResponseEntity<?> getNewComments(int limit);
    ResponseEntity<?> findCommentById(long commentId);
}
