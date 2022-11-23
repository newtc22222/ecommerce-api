package com.hcmute.ecom.model;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class Cart {
    private String id;
    private Long user_id;
    private Long discount_id;

    public Cart(String id, Long user_id, Long discount_id) {
        this.id = id;
        this.user_id = user_id;
        this.discount_id = discount_id;
    }

    public Cart(String id, Long user_id){
        this.id = id;
        this.user_id = user_id;
    }

    public Cart() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public Long getDiscountId() {
        return discount_id;
    }

    public void setDiscountId(Long discount_id) {
        this.discount_id = discount_id;
    }
}
