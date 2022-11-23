package com.hcmute.ecom.service;

import com.hcmute.ecom.model.Brand;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public interface BrandService {
    ResponseEntity<?> insert(Brand brand);
    ResponseEntity<?> update(Brand brand, long brand_id);
    ResponseEntity<?> delete(long brandId);
    ResponseEntity<?> getAllBrand();
    ResponseEntity<?> findBrandById(long brandId);
}
