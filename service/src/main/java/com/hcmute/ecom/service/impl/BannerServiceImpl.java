package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.model.Banner;
import com.hcmute.ecom.service.BannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class BannerServiceImpl implements BannerService {
    @Override
    public ResponseEntity<?> insert(Banner banner) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Banner banner, long bannerId) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long bannerId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllBanner() {
        return null;
    }

    @Override
    public ResponseEntity<?> getBannersByDateRange(LocalDate start_date, LocalDate end_date) {
        return null;
    }

    @Override
    public ResponseEntity<?> findBannerById(long bannerId) {
        return null;
    }
}
