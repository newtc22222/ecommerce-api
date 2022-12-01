package com.hcmute.ecom.dto.response;

/**
 * @author Nhat Phi
 * @since 2022-12-01
 * */
public class InvoiceStatisticDTO {
    private Integer pending;
    private Integer waitForConfirmed;
    private Integer prepared;
    private Integer isBeingShipped;
    private Integer received;
    private Integer canceled;
    private Integer failed;
    private Integer ignore;

    public InvoiceStatisticDTO() {}

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public Integer getWaitForConfirmed() {
        return waitForConfirmed;
    }

    public void setWaitForConfirmed(Integer waitForConfirmed) {
        this.waitForConfirmed = waitForConfirmed;
    }

    public Integer getPrepared() {
        return prepared;
    }

    public void setPrepared(Integer prepared) {
        this.prepared = prepared;
    }

    public Integer getIsBeingShipped() {
        return isBeingShipped;
    }

    public void setIsBeingShipped(Integer isBeingShipped) {
        this.isBeingShipped = isBeingShipped;
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
    }

    public Integer getCanceled() {
        return canceled;
    }

    public void setCanceled(Integer canceled) {
        this.canceled = canceled;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    public Integer getIgnore() {
        return ignore;
    }

    public void setIgnore(Integer ignore) {
        this.ignore = ignore;
    }
}
