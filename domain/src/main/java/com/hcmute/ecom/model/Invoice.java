package com.hcmute.ecom.model;

import com.hcmute.ecom.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class Invoice {
    private String id;
    private Long user_id;
    private Integer stock_quantity;
    private String address;
    private Double ship_fee;
    private BigDecimal total_cost;
    private Long discount_id;
    private BigDecimal tax;
    private BigDecimal final_total_cost;
    private LocalDateTime created_date;
    private String payment_type;
    private OrderStatus status;
    private boolean is_paid;
    private String note;
    private String trouble_reason; // for Cancel or Failed order

    public Invoice(String id, Long user_id, Integer stock_quantity, String address, Double ship_fee, BigDecimal total_cost,
                   Long discount_id, BigDecimal tax, BigDecimal final_total_cost, LocalDateTime created_date,
                   String payment_type, OrderStatus status, boolean is_paid, String note, String trouble_reason) {
        this.id = id;
        this.user_id = user_id;
        this.stock_quantity = stock_quantity;
        this.address = address;
        this.ship_fee = ship_fee;
        this.total_cost = total_cost;
        this.discount_id = discount_id;
        this.tax = tax;
        this.final_total_cost = final_total_cost;
        this.created_date = created_date;
        this.payment_type = payment_type;
        this.status = status;
        this.is_paid = is_paid;
        this.note = note;
        this.trouble_reason = trouble_reason;
    }

    public Invoice() {
        this.id = UUID.randomUUID().toString();
        this.created_date = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.is_paid = false;
    }

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

    public Integer getStockQuantity() {
        return stock_quantity;
    }

    public void setStockQuantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getShipFee() {
        return ship_fee;
    }

    public void setShipFee(Double ship_fee) {
        this.ship_fee = ship_fee;
    }

    public BigDecimal getTotalCost() {
        return total_cost;
    }

    public void setTotalCost(BigDecimal total_cost) {
        this.total_cost = total_cost;
    }

    public Long getDiscountId() {
        return discount_id;
    }

    public void setDiscountId(Long discount_id) {
        this.discount_id = discount_id;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getFinalTotalCost() {
        return final_total_cost;
    }

    public void setFinalTotalCost(BigDecimal final_total_cost) {
        this.final_total_cost = final_total_cost;
    }

    public LocalDateTime getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public String getPaymentType() {
        return payment_type;
    }

    public void setPaymentType(String payment_type) {
        this.payment_type = payment_type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean getPaid() {
        return is_paid;
    }

    public void setPaid(boolean paid) {
        is_paid = paid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTroubleReason() {
        return trouble_reason;
    }

    public void setTroubleReason(String trouble_reason) {
        this.trouble_reason = trouble_reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return is_paid == invoice.is_paid
                && id.equals(invoice.id)
                && user_id.equals(invoice.user_id)
                && stock_quantity.equals(invoice.stock_quantity)
                && address.equals(invoice.address)
                && ship_fee.equals(invoice.ship_fee)
                && total_cost.equals(invoice.total_cost)
                && Objects.equals(discount_id, invoice.discount_id)
                && tax.equals(invoice.tax)
                && final_total_cost.equals(invoice.final_total_cost)
                && created_date.equals(invoice.created_date)
                && payment_type.equals(invoice.payment_type)
                && status == invoice.status
                && Objects.equals(note, invoice.note)
                && Objects.equals(trouble_reason, invoice.trouble_reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, address, payment_type);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", user_id=" + user_id +
                ", stock_quantity=" + stock_quantity +
                ", address='" + address + '\'' +
                ", ship_fee=" + ship_fee +
                ", total_cost=" + total_cost +
                ", discount_id=" + discount_id +
                ", tax=" + tax +
                ", final_total_cost=" + final_total_cost +
                ", created_date=" + created_date +
                ", payment_type='" + payment_type + '\'' +
                ", status=" + status +
                ", is_paid=" + is_paid +
                ", note='" + note + '\'' +
                ", trouble_reason='" + trouble_reason + '\'' +
                '}';
    }
}
