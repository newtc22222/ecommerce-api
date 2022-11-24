package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.CommentDAO;
import com.hcmute.ecom.model.Comment;
import com.hcmute.ecom.service.CommentService;
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
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDAO commentDAO;

    @Override
    public ResponseEntity<?> insert(Comment comment) {
        return ResponseCUDObject.of(
                commentDAO.insert(comment) > 0,
                HttpStatus.CREATED,
                "Insert new comment successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new comment! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(Comment comment, long commentId) {
        Comment oldComment = commentDAO.findCommentById(commentId);

        if(oldComment == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                        HttpStatus.NOT_FOUND,
                        "Cannot find comment with id = " + commentId
                    ));
        }

        return ResponseCUDObject.of(
                commentDAO.update(comment) > 0,
                HttpStatus.OK,
                "Update comment successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update comment! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long commentId) {
        return ResponseCUDObject.of(
                commentDAO.delete(commentId) > 0,
                HttpStatus.OK,
                "Delete comment successfully!",
                HttpStatus.NOT_FOUND,
                "Cannot find comment with id = " + commentId
        );
    }

    @Override
    public ResponseEntity<?> getAllCommentsOfUser(String phone) {
        List<Comment> comments = commentDAO.getAllCommentsOfUser(phone);
        if(comments == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find comment with user has phone = " + phone));
        }

        return ResponseEntity.ok(comments);
    }

    @Override
    public ResponseEntity<?> getNewComments(int limit) {
        List<Comment> comments = commentDAO.getNewComments(limit);
        if(comments == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find any comments"));
        }

        return ResponseEntity.ok(comments);
    }

    @Override
    public ResponseEntity<?> findCommentById(long commentId) {
        Comment comment = commentDAO.findCommentById(commentId);
        if(comment == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find comment with id = " + commentId));
        }

        return ResponseEntity.ok(comment);
    }
}
