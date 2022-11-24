package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.BannerDAO;
import com.hcmute.ecom.model.Banner;
import com.hcmute.ecom.service.BannerService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDAO bannerDAO;

    @Override
    public ResponseEntity<?> insert(Banner banner) {
        return ResponseCUDObject.of(
                bannerDAO.insert(banner) > 0,
                HttpStatus.CREATED,
                "Insert new banner successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new banner! Please check your input again!"
        );
    }

    @Override
    public ResponseEntity<?> update(Banner banner, long bannerId) {
        Banner oldBanner = bannerDAO.findBannerById(bannerId);
        
        if(oldBanner == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find banner with id = " + bannerId
                    ));
        }
        else {
            oldBanner.setPath(banner.getPath());
            oldBanner.setType(banner.getType());
            oldBanner.setUsedDate(banner.getUsedDate());
            oldBanner.setEndedDate(banner.getEndedDate());
        }
        
        return ResponseCUDObject.of(
                bannerDAO.update(oldBanner) > 0,
                HttpStatus.OK,
                "Update banner's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update banner! Please check your input again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long bannerId) {
        return ResponseCUDObject.of(
                bannerDAO.delete(bannerId) > 0,
                HttpStatus.CREATED,
                "Delete banner successfully!",
                HttpStatus.NOT_FOUND,
                "Cannot find banner with id = " + bannerId
        );
    }

    @Override
    public ResponseEntity<?> getAllBanner() {
        List<Banner> banners = bannerDAO.getAllBanner();
        if (banners == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any banners!"
                    ));
        }
        return ResponseEntity.ok(banners);
    }

    @Override
    public ResponseEntity<?> getBannersByDateRange(LocalDate start_date, LocalDate end_date) {
        List<Banner> banners = bannerDAO.getAllBannerByDateRange(Date.valueOf(start_date), Date.valueOf(end_date));
        if(banners == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find data in conditions!"
                    ));
        }

        return ResponseEntity.ok(banners);
    }

    @Override
    public ResponseEntity<?> findBannerById(long bannerId) {
        Banner banner = bannerDAO.findBannerById(bannerId);
        if(banner == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find banner with id = " + bannerId));
        }

        return ResponseEntity.ok(banner);
    }
}
