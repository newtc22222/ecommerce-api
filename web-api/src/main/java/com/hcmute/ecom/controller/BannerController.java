package com.hcmute.ecom.controller;

import com.hcmute.ecom.model.Banner;
import com.hcmute.ecom.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/banners")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("")
    public ResponseEntity<?> getAllBanners(){
        return bannerService.getAllBanner();
    }
    public ResponseEntity<?> getBannersByDateRange(
            @RequestParam(value = "start", defaultValue = "2022-10-01") LocalDate startDate,
            @RequestParam(value = "end", defaultValue = "2022-11-01") LocalDate endDate){
        return bannerService.getBannersByDateRange(startDate, endDate);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findBannerById(@PathVariable("id") long bannerId) {
        return bannerService.findBannerById(bannerId);
    }

    @PostMapping("")
    public ResponseEntity<?> insertBanner(@RequestBody Map<String, String> bannerRequest) {
        Banner banner = new Banner(
                Long.getLong(bannerRequest.get("id")),
                bannerRequest.get("path"),
                bannerRequest.get("type"),
                Date.valueOf(bannerRequest.get("usedDate")),
                Date.valueOf(bannerRequest.get("endedDate")));
        return bannerService.insert(banner);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBanner(@PathVariable("id") long bannerId, @RequestBody Map<String, String> bannerRequest) {
        Banner banner = new Banner(
                Long.getLong(bannerRequest.get("id")),
                bannerRequest.get("path"),
                bannerRequest.get("type"),
                Date.valueOf(bannerRequest.get("usedDate")),
                Date.valueOf(bannerRequest.get("endedDate")));
        System.out.println(banner);
        return bannerService.update(banner, bannerId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBanner(@PathVariable("id") long bannerId) {
        return bannerService.delete(bannerId);
    }
}
