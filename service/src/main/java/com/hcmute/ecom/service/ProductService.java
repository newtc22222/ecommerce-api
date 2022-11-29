package com.hcmute.ecom.service;

import com.hcmute.ecom.dto.request.LaptopDTORequest;
import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.model.laptop.Laptop;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface ProductService {
    ResponseEntity<?> insert(Laptop product);
    ResponseEntity<?> updateAll(Laptop product, String productId);
    ResponseEntity<?> updatePrice(ProductDTORequest productDTO);
    ResponseEntity<?> updateLaptopProperties(LaptopDTORequest laptopDTO);
    ResponseEntity<?> delete(String productId);
    ResponseEntity<?> insertDiscount(String productId, long discountId);
    ResponseEntity<?> deleteDiscount(String productId, long discountId);
    ResponseEntity<?> getAllProduct();
    ResponseEntity<?> findProductById(String productId);

    ResponseEntity<?> getProductsByBrand(long brandId);

    // For searching...
    ResponseEntity<?> filter(Map<String, String> params);
}
