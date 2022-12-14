package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.Banner;

import java.sql.Date;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-20
 * */
public interface BannerDAO {
    int insert(Banner banner);
    int update(Banner banner);
    int delete(long bannerId);
    List<Banner> getAllBanner();
    /**
     * Find banner is being used in system
     * */
    List<Banner> getAllBannerByDateRange(Date start_date, Date end_date);
    List<Banner> getBannersByDate(Date date);
    List<Banner> getBannersByType(String type);
    Banner findBannerById(long bannerId);
}
