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
    List<Banner> getAllBannerByDateRange(Date start_date, Date end_date);
    Banner findBannerById(long bannerId);
}
