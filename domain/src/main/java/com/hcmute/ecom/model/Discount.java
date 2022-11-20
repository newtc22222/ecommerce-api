package com.hcmute.ecom.model;

import com.hcmute.ecom.enums.DiscountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class Discount {
    private Long id;
    private String code;
    private Float rate;
    private DiscountType applied_type;
    private BigDecimal max_amount;
    private LocalDateTime applied_date;
    private LocalDateTime ended_date;

    public Discount(Long id, String code, Float rate, DiscountType applied_type, BigDecimal max_amount, LocalDateTime applied_date, LocalDateTime ended_date) {
        this.id = id;
        this.code = code;
        this.rate = rate;
        this.applied_type = applied_type;
        this.max_amount = max_amount;
        this.applied_date = applied_date;
        this.ended_date = ended_date;
    }

    public Discount() {
        this.id = 0L;
        this.applied_type = DiscountType.PRODUCT;
        this.applied_date = LocalDateTime.now();
        this.ended_date = LocalDateTime.now().plusDays(7); // default is 7 days
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DiscountType getDiscountType() {
        return applied_type;
    }

    public void setDiscountType(DiscountType applied_type) {
        this.applied_type = applied_type;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public BigDecimal getMaxAmount() {
        return max_amount;
    }

    public void setMaxAmount(BigDecimal max_amount) {
        this.max_amount = max_amount;
    }

    public LocalDateTime getAppliedDate() {
        return applied_date;
    }

    public void setAppliedDate(LocalDateTime applied_date) {
        this.applied_date = applied_date;
    }

    public LocalDateTime getEndedDate() {
        return ended_date;
    }

    public void setEndedDate(LocalDateTime ended_date) {
        this.ended_date = ended_date;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", applied_type=" + applied_type +
                ", rate=" + rate +
                ", max_amount=" + max_amount +
                ", applied_date=" + applied_date +
                ", ended_date=" + ended_date +
                '}';
    }
}
