package com.hcmute.ecom.service;

import com.hcmute.ecom.model.Banner;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface BannerService {
    ResponseEntity<?> insert(Banner banner);
    ResponseEntity<?> update(Banner banner, long bannerId);
    ResponseEntity<?> delete(long bannerId);
    ResponseEntity<?> getAllBanner();
    ResponseEntity<?> findBannerById(long bannerId);
    ResponseEntity<?> filter(Map<String, String> params);
}
