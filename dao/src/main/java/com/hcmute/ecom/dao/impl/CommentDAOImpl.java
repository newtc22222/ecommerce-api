package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.CommentDAO;
import com.hcmute.ecom.mapper.CommentMapper;
import com.hcmute.ecom.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
@Component
public class CommentDAOImpl implements CommentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_comment";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set root_comment_id=?, product_id=?, username=?, phone=?, content=?, created_date=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

//    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ALL_COMMENTS_OF_PRODUCT = String.format("select * from %s where product_id=?", TABLE_NAME);
    private final String QUERY_ALL_COMMENTS_OF_USER = String.format("select * from %s where phone=?", TABLE_NAME);
    private final String QUERY_NEW_COMMENTS = String.format("select * from %s " +
            "order by created_date desc limit ?", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=?", TABLE_NAME);

    @Override
    public int insert(Comment comment) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    comment.getRootCommentId(),
                    comment.getProductId(),
                    comment.getUsername(),
                    comment.getPhone(),
                    comment.getContent(),
                    Timestamp.valueOf(comment.getCreatedDate())
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(Comment comment) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    comment.getRootCommentId(),
                    comment.getProductId(),
                    comment.getUsername(),
                    comment.getPhone(),
                    comment.getContent(),
                    Timestamp.valueOf(comment.getCreatedDate()),
                    comment.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long commentId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    commentId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<Comment> getAllCommentsOfProduct(String productId) {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL_COMMENTS_OF_PRODUCT,
                    new CommentMapper(),
                    productId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Comment> getAllCommentsOfUser(String phone) {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL_COMMENTS_OF_USER,
                    new CommentMapper(),
                    phone
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Comment> getNewComments(int limit) {
        try {
            return jdbcTemplate.query(
                    QUERY_NEW_COMMENTS,
                    new CommentMapper(),
                    limit
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Comment findCommentById(long commentId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new CommentMapper(),
                    commentId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
