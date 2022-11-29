package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class CommentMapper implements RowMapper<Comment> {
    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("id"));
        comment.setRootCommentId(rs.getLong("root_comment_id"));
        comment.setProductId(rs.getString("product_id"));
        comment.setUsername(rs.getNString("username"));
        comment.setPhone(rs.getString("phone"));
        comment.setContent(rs.getNString("content"));
        comment.setCreatedDate(LocalDateTime.parse(rs.getString("created_date"), DATE_TIME_PATTERN));
        return comment;
    }
}
