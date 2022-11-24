package com.hcmute.ecom.controller;

import com.hcmute.ecom.model.Brand;
import com.hcmute.ecom.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("api/v1/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("")
    public ResponseEntity<?> getAllBrand(){
        return brandService.getAllBrand();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBrandById(@PathVariable("id") long brandId){
        return brandService.findBrandById(brandId);
    }

    @PostMapping("")
    public ResponseEntity<?> insertNewBrand(@RequestBody Brand brand){
        return brandService.insert(brand);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBrand(@PathVariable("id") long brandId, @RequestBody Brand brand){
        return brandService.update(brand, brandId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") long brandId){
        return brandService.delete(brandId);
    }
}
