package com.hcmute.ecom.controller;

import com.hcmute.ecom.model.Brand;
import com.hcmute.ecom.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
@Api(tags = "Brand of Product", value = "Brand Controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("api/v1/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @ApiOperation(value = "Get all brands in system", response = Brand.class)
    @GetMapping("")
    public ResponseEntity<?> getAllBrand(){
        return brandService.getAllBrand();
    }

    @ApiOperation(value = "Get one brand in system with id", response = Brand.class)
    @GetMapping("{id}")
    public ResponseEntity<?> getBrandById(@PathVariable("id") long brandId){
        return brandService.findBrandById(brandId);
    }

    @ApiOperation(value = "Create a new brand", response = ResponseEntity.class)
    @PostMapping("")
    public ResponseEntity<?> createNewBrand(@RequestBody Brand brand){
        return brandService.insert(brand);
    }

    @ApiOperation(value = "Update a brand", response = ResponseEntity.class)
    @PutMapping("{id}")
    public ResponseEntity<?> updateBrand(@PathVariable("id") long brandId, @RequestBody Brand brand){
        return brandService.update(brand, brandId);
    }

    @ApiOperation(value = "Remove a brand in system", response = ResponseEntity.class)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") long brandId){
        return brandService.delete(brandId);
    }
}
