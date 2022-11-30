package com.hcmute.ecom.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author Nhat Phi
 * @since 2022-11-20
 * */
public class Banner {
    private Long id;
    private String path;
    private String type;
    private Date used_date;
    private Date ended_date;

    public Banner(Long id, String path, String type, Date used_date, Date ended_date) {
        this.id = id;
        this.path = path;
        this.type = type;
        this.used_date = used_date;
        this.ended_date = ended_date;
    }

    public Banner() {
        this.id = 0L;
        this.used_date = Date.valueOf(LocalDate.now());
        this.ended_date = Date.valueOf(LocalDate.now().plusDays(7));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUsedDate() {
        return used_date;
    }

    public void setUsedDate(Date used_date) {
        this.used_date = used_date;
    }

    public Date getEndedDate() {
        return ended_date;
    }

    public void setEndedDate(Date ended_date) {
        this.ended_date = ended_date;
    }
}
