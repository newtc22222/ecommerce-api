package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.CommentDTO;
import com.hcmute.ecom.model.Comment;
import com.hcmute.ecom.service.CommentService;
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
@Api(tags = "Comment of product", value = "Comment controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Get all comments (option: limit)", response = Comment.class)
    @GetMapping("/new-comments")
    public ResponseEntity<?> getAllComments(@RequestParam(value = "limit", defaultValue = "10") int limit) {
        return commentService.getNewComments(limit);
    }

    @ApiOperation(value = "Get all comments of a product", response = Comment.class)
    @GetMapping("/products/{productId}/comments")
    public ResponseEntity<?> getCommentsOfProduct(@PathVariable("productId") String productId) {
        return commentService.getAllCommentsOfProduct(productId);
    }

    @ApiOperation(value = "Get all comments of a user", response = Comment.class)
    @GetMapping("/users/{phoneNumber}/comments")
    public ResponseEntity<?> getCommentsOfUser(@RequestParam("phoneNumber") String phone) {
        return commentService.getAllCommentsOfUser(phone);
    }

    @ApiOperation(value = "Get one comment with id", response = Comment.class)
    @GetMapping("/comments/{id}")
    public ResponseEntity<?> findCommentById(@PathVariable("id") long commentId) {
        return commentService.findCommentById(commentId);
    }

    @ApiOperation(value = "Create a new comment", response = ResponseEntity.class)
    @PostMapping("/comments")
    public ResponseEntity<?> createNewComment(@RequestBody CommentDTO commentDTO) {
        return commentService.insert(CommentDTO.transform(commentDTO));
    }

    @ApiOperation(value = "Update a comment", response = ResponseEntity.class)
    @PutMapping("/comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") long commentId,
                                           @RequestBody CommentDTO commentDTO) {
        return commentService.update(CommentDTO.transform(commentDTO), commentId);
    }

    @ApiOperation(value = "Remove a comment", response = ResponseEntity.class)
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") long commentId) {
        return commentService.delete(commentId);
    }
}
