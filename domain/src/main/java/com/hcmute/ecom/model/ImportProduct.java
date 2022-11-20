package com.hcmute.ecom.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-20
 * */
public class ImportProduct {
    private Long ticket_id;
    private String product_id;
    private Long quantity;
    private BigDecimal imported_price;
    private LocalDateTime imported_date;

    public ImportProduct(Long ticket_id, String product_id, Long quantity, BigDecimal imported_price,
                         LocalDateTime imported_date) {
        this.ticket_id = ticket_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.imported_price = imported_price;
        this.imported_date = imported_date;
    }

    public Long getTicketId() {
        return ticket_id;
    }

    public void setTicketId(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getImportedPrice() {
        return imported_price;
    }

    public void setImportedPrice(BigDecimal imported_price) {
        this.imported_price = imported_price;
    }

    public LocalDateTime getImportedDate() {
        return imported_date;
    }

    public void setImportedDate(LocalDateTime imported_date) {
        this.imported_date = imported_date;
    }

    @Override
    public String toString() {
        return "ImportProduct{" +
                "ticket_id=" + ticket_id +
                ", product_id='" + product_id + '\'' +
                ", quantity=" + quantity +
                ", imported_price=" + imported_price +
                ", imported_date=" + imported_date +
                '}';
    }
}
