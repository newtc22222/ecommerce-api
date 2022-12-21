package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.model.Invoice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
@ApiModel("Class representing for invoice request body")
public class InvoiceDTO {
    @ApiModelProperty(required = true)
    private Long userId;
    @ApiModelProperty(required = true)
    private Integer stockQuantity;
    @ApiModelProperty(required = true)
    private String address;
    @ApiModelProperty(required = true)
    private BigDecimal totalCost;

    private Double shipFee;
    private Long discountId;
    private BigDecimal tax;

    @ApiModelProperty(required = true)
    private BigDecimal finalTotalCost;
    @ApiModelProperty(required = true)
    private String paymentType;
    @ApiModelProperty(required = true)
    private String status;
    @ApiModelProperty(required = true)
    private boolean isPaid;

    private String createdDate;
    private String note;
    private String troubleReason;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getShipFee() {
        return shipFee;
    }

    public void setShipFee(Double shipFee) {
        this.shipFee = shipFee;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getFinalTotalCost() {
        return finalTotalCost;
    }

    public void setFinalTotalCost(BigDecimal finalTotalCost) {
        this.finalTotalCost = finalTotalCost;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTroubleReason() {
        return troubleReason;
    }

    public void setTroubleReason(String troubleReason) {
        this.troubleReason = troubleReason;
    }

    public static Invoice transform(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        invoice.setUserId(invoiceDTO.getUserId());
        invoice.setStockQuantity(invoiceDTO.getStockQuantity());
        invoice.setAddress(invoiceDTO.getAddress());
        if (invoiceDTO.getShipFee() != null) {
            invoice.setShipFee(invoiceDTO.getShipFee());
        }
        invoice.setTotalCost(invoiceDTO.getTotalCost());
        if(invoiceDTO.getDiscountId() != null) {
            invoice.setDiscountId(invoiceDTO.getDiscountId());
        }
        if (invoiceDTO.getTax() != null) {
            invoice.setTax(invoiceDTO.getTax());
        }
        invoice.setFinalTotalCost(invoiceDTO.getFinalTotalCost());
        if (invoiceDTO.getCreatedDate() != null) {
            invoice.setCreatedDate(LocalDateTime.parse(invoiceDTO.getCreatedDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        invoice.setPaymentType(invoiceDTO.getPaymentType());
        invoice.setStatus(OrderStatus.valueOf(invoiceDTO.getStatus()));
        invoice.setPaid(invoiceDTO.getPaid());
        if (invoiceDTO.getNote() != null) {
            invoice.setNote(invoiceDTO.getNote());
        }
        if (invoiceDTO.getTroubleReason() != null) {
            invoice.setTroubleReason(invoiceDTO.getTroubleReason());
        }
        return invoice;
    }
}
