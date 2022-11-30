package com.hcmute.ecom.model;

import java.sql.Date;

/**
 * Brand logo will save as Blob (in Spring is String)
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public class Brand {
    private Long id;
    private String name;
    private String country;
    private Date establish_date;
    private String logo;

    public Brand(Long id, String name, String country, Date establish_date, String logo) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.establish_date = establish_date;
        this.logo = logo;
    }

    public Brand() {
        this.id = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getEstablishDate() {
        return establish_date;
    }

    public void setEstablishDate(Date establish_date) {
        this.establish_date = establish_date;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", establish_date=" + establish_date +
                ", logo='" + logo + '\'' +
                '}';
    }
}
