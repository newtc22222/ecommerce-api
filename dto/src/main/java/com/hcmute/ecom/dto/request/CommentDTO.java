package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.model.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
@ApiModel("Class representing a comment request body")
public class CommentDTO {
    private Long rootCommentId;
    private String createdDate;
    @ApiModelProperty(required = true)
    private String productId;
    @ApiModelProperty(required = true)
    private String username;
    @ApiModelProperty(required = true)
    private String phone;
    @ApiModelProperty(required = true)
    private String content;

    public Long getRootCommentId() {
        return rootCommentId;
    }

    public void setRootCommentId(Long rootCommentId) {
        this.rootCommentId = rootCommentId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Comment transform(CommentDTO commentDTO) {
        Comment comment = new Comment();
        if(commentDTO.getRootCommentId() != null){
            comment.setRootCommentId(commentDTO.getRootCommentId());
        }
        comment.setProductId(commentDTO.getProductId());
        comment.setUsername(commentDTO.getUsername());
        comment.setPhone(commentDTO.getPhone());
        comment.setContent(commentDTO.getContent());

        if(commentDTO.getCreatedDate() != null) {
            comment.setCreatedDate(LocalDateTime.parse(commentDTO.getCreatedDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        return comment;
    }
}
