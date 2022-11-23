package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.Comment;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface CommentDAO {
    int insert(Comment comment);
    int update(Comment comment);
    int delete(long commentId);
    List<Comment> getAllCommentsOfProduct(String productId);
    /**
     * Comment will be gotten by user phone number
     * */
    List<Comment> getAllCommentsOfUser(String phone);
    /**
     * New (limit) number comments will be gotten
     * */
    List<Comment> getNewComments(int limit);
    Comment findCommentById(long commentId);
}
