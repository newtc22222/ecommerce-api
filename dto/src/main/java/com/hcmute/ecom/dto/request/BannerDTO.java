package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.model.Banner;

import java.sql.Date;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-25
 * */
public class BannerDTO {
    public static Banner transform(Map<String, String> request) {
        Banner banner = new Banner();
        banner.setPath(request.get("path"));
        banner.setType(request.get("type"));
        banner.setUsedDate(Date.valueOf(request.get("usedDate")));
        banner.setEndedDate(Date.valueOf(request.get("endedDate")));
        return banner;
    }
}
