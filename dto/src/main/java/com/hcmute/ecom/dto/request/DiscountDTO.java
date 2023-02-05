package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.DiscountType;
import com.hcmute.ecom.model.Discount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
@ApiModel("Class representing for discount request body")
public class DiscountDTO {
    @ApiModelProperty(required = true)
    private String code;
    @ApiModelProperty(required = true, example = "0.5", notes = "value in range 0 to 1")
    private Float rate;
    private String appliedType;
    @ApiModelProperty(required = true)
    private BigDecimal maxAmount;
    @ApiModelProperty(required = true)
    private String appliedDate;
    @ApiModelProperty(required = true)
    private String endedDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getAppliedType() {
        return appliedType;
    }

    public void setAppliedType(String appliedType) {
        this.appliedType = appliedType;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(String endedDate) {
        this.endedDate = endedDate;
    }

    public static Discount transform(DiscountDTO discountDTO) {
         Discount discount = new Discount();
         discount.setCode(discountDTO.getCode());
         discount.setRate(discountDTO.getRate());

         if (discountDTO.getAppliedType() != null){
            discount.setDiscountType(DiscountType.valueOf(discountDTO.getAppliedType()));
         }

         discount.setMaxAmount(discountDTO.getMaxAmount());
         discount.setAppliedDate(LocalDateTime.parse(discountDTO.getAppliedDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
         discount.setEndedDate(LocalDateTime.parse(discountDTO.getEndedDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
         return discount;
    }
}
