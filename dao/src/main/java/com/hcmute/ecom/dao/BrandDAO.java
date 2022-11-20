package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.Brand;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
public interface BrandDAO {
    int insert(Brand brand);
    int update(Brand brand);
    int delete(long brandId);
    List<Brand> getAllBrand();
    Brand findBrandById(long brandId);
}
