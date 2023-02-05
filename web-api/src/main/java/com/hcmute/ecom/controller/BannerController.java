package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.BannerDTO;
import com.hcmute.ecom.model.Banner;
import com.hcmute.ecom.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Api(tags = "Big image for advertise", value = "Banner Controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/banners")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "Get all banners in system", response = Banner.class)
    @GetMapping("")
    public ResponseEntity<?> getBanners(@RequestParam(value = "start", required = false) String startDate,
                                        @RequestParam(value = "end", required = false) String endDate,
                                        @RequestParam(value = "date", required = false) String date,
                                        @RequestParam(value = "type", required = false) String type) {
        if(startDate == null && endDate == null && date == null && type == null) {
            return bannerService.getAllBanner();
        }

        Map<String, String> params = new HashMap<>();
        if(startDate != null) params.put("startDate", startDate);
        if(endDate != null) params.put("endDate", endDate);
        if(date != null) params.put("date", date);
        if(type != null) params.put("type", type);
        return bannerService.filter(params);
    }

    @ApiOperation(value = "Get banner by bannerId", response = Banner.class)
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> findBannerById(@PathVariable("id") long bannerId) {
        return bannerService.findBannerById(bannerId);
    }

    @ApiOperation(value = "Create new banner", response = ResponseEntity.class)
    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> insertBanner(@RequestBody BannerDTO bannerDTO) {
        return bannerService.insert(BannerDTO.transform(bannerDTO));
    }

    @ApiOperation(value = "Change banner information", response = ResponseEntity.class)
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateBanner(@PathVariable("id") long bannerId,
                                          @RequestBody BannerDTO bannerDTO) {
        return bannerService.update(BannerDTO.transform(bannerDTO), bannerId);
    }

    @ApiOperation(value = "Delete banner", response = ResponseEntity.class)
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteBanner(@PathVariable("id") long bannerId) {
        return bannerService.delete(bannerId);
    }
}
