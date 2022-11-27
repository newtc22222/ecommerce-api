package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.model.Brand;

import java.sql.Date;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
public class BrandDTO {
    public static Brand transform(Map<String, String> request) {
        Brand brand = new Brand();
        brand.setName(request.get("name"));
        brand.setCountry(request.get("country"));

        if(request.get("establishDate") != null) {
            brand.setEstablishDate(Date.valueOf(request.get("establishDate")));
        }

        brand.setLogo(request.get("logo"));
        return brand;
    }
}
