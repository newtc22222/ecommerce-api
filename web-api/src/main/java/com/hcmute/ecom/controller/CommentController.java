package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.CommentDTO;
import com.hcmute.ecom.model.Comment;
import com.hcmute.ecom.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/new-comments")
    public ResponseEntity<?> getAllComments(@RequestParam(value = "limit", defaultValue = "10") int limit) {
        return commentService.getNewComments(limit);
    }

    @GetMapping("/products/{productId}/comments")
    public ResponseEntity<?> getCommentsOfProduct(@PathVariable("productId") String productId) {
        return commentService.getAllCommentsOfProduct(productId);
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getCommentsOfUser(@RequestParam("phoneNumber") String phone) {
        return commentService.getAllCommentsOfUser(phone);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<?> findCommentById(@PathVariable("id") long commentId) {
        return commentService.findCommentById(commentId);
    }

    @PostMapping("/comments")
    public ResponseEntity<?> createNewComment(@RequestBody Map<String, String> commentRequest) {
        return commentService.insert(CommentDTO.transform(commentRequest));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") long commentId, @RequestBody Map<String, String> commentRequest) {
        return commentService.update(CommentDTO.transform(commentRequest), commentId);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") long commentId) {
        return commentService.delete(commentId);
    }
}
