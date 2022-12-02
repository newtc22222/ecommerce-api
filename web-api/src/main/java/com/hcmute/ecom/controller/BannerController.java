package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.BannerDTO;
import com.hcmute.ecom.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Api(tags = "Banner Controller", value = "banner CRUD and filter")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/banners")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @ApiOperation("Get all banners in system")
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

    @ApiOperation("Get banner by bannerId")
    @GetMapping("{id}")
    public ResponseEntity<?> findBannerById(@PathVariable("id") long bannerId) {
        return bannerService.findBannerById(bannerId);
    }

    @ApiOperation("Create new banner")
    @PostMapping("")
    public ResponseEntity<?> insertBanner(@RequestBody Map<String, String> bannerRequest) {
        return bannerService.insert(BannerDTO.transform(bannerRequest));
    }

    @ApiOperation("Update banner information")
    @PutMapping("{id}")
    public ResponseEntity<?> updateBanner(@PathVariable("id") long bannerId, @RequestBody Map<String, String> bannerRequest) {
        return bannerService.update(BannerDTO.transform(bannerRequest), bannerId);
    }

    @ApiOperation("Remove banner")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBanner(@PathVariable("id") long bannerId) {
        return bannerService.delete(bannerId);
    }
}
