package com.hcmute.ecom.model;

/**
 * <h3>Note</h3>
 * <ul>
 *     <li>Country: default - "Việt Nam"</li>
 *     <li>line1: big city, province</li>
 *     <li>line2: city, district, town</li>
 *     <li>line3: commune</li>
 * </ul>
 * @author Nhat Phi
 * @since 2022-11-8
 */
public class Address {
    private Long id;
    private Long user_id;
    private String country;
    private String line1;
    private String line2;
    private String line3;
    private String street;

    public Address(Long id, Long user_id, String country, String line1, String line2, String line3, String street) {
        this.id = id;
        this.user_id = user_id;
        this.country = country;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.street = street;
    }

    public Address() {
        this.id = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
