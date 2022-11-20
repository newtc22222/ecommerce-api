package com.hcmute.ecom.model;

import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class Feedback {
    private Long id;
    private String product_id;
    private Long user_id;
    private String content;
    private LocalDateTime created_date;
    private Byte rating_point;

    public Feedback(Long id, String product_id, Long user_id, String content, LocalDateTime created_date, Byte rating_point) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.content = content;
        this.created_date = created_date;
        this.rating_point = rating_point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getRatingPoint() {
        return rating_point;
    }

    public void setRatingPoint(Byte rating_point) {
        this.rating_point = rating_point;
    }

    public LocalDateTime getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", product_id='" + product_id + '\'' +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", rating_point=" + rating_point +
                ", created_date=" + created_date +
                '}';
    }
}
