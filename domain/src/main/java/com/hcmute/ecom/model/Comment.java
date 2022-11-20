package com.hcmute.ecom.model;

import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class Comment {
    private Long id;
    private Long root_comment_id;
    private String product_id;
    private String username;
    private String phone;
    private String content;
    private LocalDateTime created_date;

    public Comment(Long id, Long root_comment_id, String product_id, String username, String phone, String content, LocalDateTime created_date) {
        this.id = id;
        this.root_comment_id = root_comment_id;
        this.product_id = product_id;
        this.username = username;
        this.phone = phone;
        this.content = content;
        this.created_date = created_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRootCommentId() {
        return root_comment_id;
    }

    public void setRootCommentId(Long root_comment_id) {
        this.root_comment_id = root_comment_id;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
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

    public LocalDateTime getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", root_comment_id=" + root_comment_id +
                ", product_id='" + product_id + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                ", created_date=" + created_date +
                '}';
    }
}
