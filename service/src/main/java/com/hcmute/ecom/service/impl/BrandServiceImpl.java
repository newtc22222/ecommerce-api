package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.BrandDAO;
import com.hcmute.ecom.model.Brand;
import com.hcmute.ecom.service.BrandService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDAO brandDAO;

    @Override
    public ResponseEntity<?> insert(Brand brand) {
        return ResponseCUDObject.of(
                brandDAO.insert(brand) > 0,
                HttpStatus.CREATED,
                "Insert new brand successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new brand! Please check your data again!");
    }

    @Override
    public ResponseEntity<?> update(Brand brand, long brandId) {
        Brand oldBrand = brandDAO.findBrandById(brandId);
        
        if(oldBrand == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find brand with id = " + brandId
                    ));
        }
        else {
            // change information
            oldBrand.setName(brand.getName());
            oldBrand.setCountry(brand.getCountry());
            oldBrand.setLogo(brand.getLogo());
            oldBrand.setEstablishDate(brand.getEstablishDate());
        }
        
        return ResponseCUDObject.of(
                brandDAO.update(oldBrand) > 0,
                HttpStatus.OK,
                "Update brand's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update new brand! Please check your data again!");
    }

    @Override
    public ResponseEntity<?> delete(long brandId) {
        return ResponseCUDObject.of(
                brandDAO.delete(brandId) > 0,
                HttpStatus.OK,
                "Delete brand successfully!",
                HttpStatus.NOT_FOUND,
                "Cannot find brand with id = " + brandId);
    }

    @Override
    public ResponseEntity<?> getAllBrand() {
        List<Brand> brands = brandDAO.getAllBrand();
        if (brands == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find address of this user!"
                    ));
        }
        return ResponseEntity.ok(brands);
    }

    @Override
    public ResponseEntity<?> findBrandById(long brandId) {
        Brand brand = brandDAO.findBrandById(brandId);
        if(brand == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find brand with id = " + brandId));
        }

        return ResponseEntity.ok(brand);
    }
}
