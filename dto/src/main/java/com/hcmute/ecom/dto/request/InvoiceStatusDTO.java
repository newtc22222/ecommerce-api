package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.enums.OrderStatus;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
public class InvoiceStatusDTO {
    private String id;
    private OrderStatus status;
    private String paymentType;
    private boolean isPaid;
    private String note;
    private String troubleReason;

    public InvoiceStatusDTO(String id, OrderStatus status, String paymentType, boolean isPaid,
                            String note, String troubleReason) {
        this.id = id;
        this.status = status;
        this.paymentType = paymentType;
        this.isPaid = isPaid;
        this.note = note;
        this.troubleReason = troubleReason;
    }

    public InvoiceStatusDTO() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
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
}
