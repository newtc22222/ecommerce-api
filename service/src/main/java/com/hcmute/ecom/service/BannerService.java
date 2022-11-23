package com.hcmute.ecom.service;

import com.hcmute.ecom.model.Banner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface BannerService {
    ResponseEntity<?> insert(Banner banner);
    ResponseEntity<?> update(Banner banner, long bannerId);
    ResponseEntity<?> delete(long bannerId);
    ResponseEntity<?> getAllBanner();
    ResponseEntity<?> getBannersByDateRange(LocalDate start_date, LocalDate end_date);
    ResponseEntity<?> findBannerById(long bannerId);
}
