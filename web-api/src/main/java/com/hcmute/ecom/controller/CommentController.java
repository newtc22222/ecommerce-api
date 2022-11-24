package com.hcmute.ecom.controller;

import com.hcmute.ecom.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAllComments(@RequestParam("limit") int limit) {
        return commentService.getNewComments(limit);
    }

    @GetMapping("/products/{productId}/comments")
    public ResponseEntity<?> getCommentsOfProduct(@PathVariable("productId") String productId) {
        return commentService.getAllCommentsOfProduct(productId);
    }
}
