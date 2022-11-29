package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.BannerDTO;
import com.hcmute.ecom.model.Banner;
import com.hcmute.ecom.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public ResponseEntity<?> getBanners(
            @RequestParam(value = "start", defaultValue = "2022-10-01", required = false) String startDate,
            @RequestParam(value = "end", defaultValue = "2022-11-01", required = false) String endDate) {
        if(startDate != null && endDate != null){
            LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return bannerService.getBannersByDateRange(start, end);
        }
        else {
            return bannerService.getAllBanner();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findBannerById(@PathVariable("id") long bannerId) {
        return bannerService.findBannerById(bannerId);
    }

    @PostMapping("")
    public ResponseEntity<?> insertBanner(@RequestBody Map<String, String> bannerRequest) {
        return bannerService.insert(BannerDTO.transform(bannerRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBanner(@PathVariable("id") long bannerId, @RequestBody Map<String, String> bannerRequest) {
        return bannerService.update(BannerDTO.transform(bannerRequest), bannerId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBanner(@PathVariable("id") long bannerId) {
        return bannerService.delete(bannerId);
    }
}
