package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.BrandDAO;
import com.hcmute.ecom.model.Brand;
import com.hcmute.ecom.service.BrandService;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return (brandDAO.insert(brand) > 0)
                ? ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseObject(
                            HttpStatus.OK,
                            "Insert new brand successfully!"))
                : ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject(
                        HttpStatus.NOT_IMPLEMENTED,
                        "Failed to insert new brand! Please check your data again!"));
    }

    @Override
    public ResponseEntity<?> update(Brand brand, long brandId) {
        if(brandDAO.findBrandById(brandId) == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Can not find brand with id = " + brandId));
        }

        // change information
        Brand newBrand = brandDAO.findBrandById(brandId);
        newBrand.setName(brand.getName());
        newBrand.setCountry(brand.getCountry());
        newBrand.setLogo(brand.getLogo());
        newBrand.setEstablishDate(brand.getEstablishDate());

        return (brandDAO.update(newBrand) > 0)
                ? ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseObject(
                        HttpStatus.OK,
                        "Update brand's information successfully!"))
                : ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject(
                            HttpStatus.NOT_IMPLEMENTED,
                            "Failed to update brand! Please check your data again!"));
    }

    @Override
    public ResponseEntity<?> delete(long brandId) {
        return (brandDAO.delete(brandId) > 0)
                ? ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseObject(
                        HttpStatus.OK,
                        "Delete brand successfully!"))
                : ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Can not find brand with id = " + brandId));
    }

    @Override
    public ResponseEntity<?> getAllBrand() {
        return ResponseEntity.ok(brandDAO.getAllBrand());
    }

    @Override
    public ResponseEntity<?> findBrandById(long brandId) {
        if(brandDAO.findBrandById(brandId) == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Can not find brand with id = " + brandId));
        }

        return ResponseEntity.ok(brandDAO.findBrandById(brandId));
    }
}
