package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.model.Comment;
import com.hcmute.ecom.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public ResponseEntity<?> insert(Comment comment) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Comment comment) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long commentId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllCommentsOfUser(String phone) {
        return null;
    }

    @Override
    public ResponseEntity<?> getNewComments(int limit) {
        return null;
    }

    @Override
    public ResponseEntity<?> findCommentById(long commentId) {
        return null;
    }
}
