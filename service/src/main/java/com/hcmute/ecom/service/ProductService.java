package com.hcmute.ecom.service;

import com.hcmute.ecom.dto.request.LaptopDTORequest;
import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.Product;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface ProductService {
    ResponseEntity<?> insert(Product product);
    ResponseEntity<?> updateAll(Product product, String productId);
    ResponseEntity<?> updatePrice(ProductDTORequest productDTO);
    ResponseEntity<?> updateLaptopProperties(LaptopDTORequest laptopDTO);
    ResponseEntity<?> delete(String productId);
    ResponseEntity<?> getAllProduct();
    ResponseEntity<?> findProductById(String productId);

    // For searching...
    ResponseEntity<?> findProductsByConditions(Object ...args);

//    ResponseEntity<?> findProductsByName(String name);
//    ResponseEntity<?> getProductsByBrand(long brandId);
//    ResponseEntity<?> getProductsByCategory(long categoryId);
//    ResponseEntity<?> getProductsByReleasedYear(int year);
//    ResponseEntity<?> getProductsByPriceRange(BigDecimal startPrice, BigDecimal endPrice);
//    ResponseEntity<?> getLaptopsByRamCapacity(int ramCapacity);
//    ResponseEntity<?> getLaptopsByCPU(String cpuBrand, String cpuType);
//    ResponseEntity<?> getLaptopsByScreenSize(float screenSize);
//    ResponseEntity<?> getLaptopsByGraphicCardType(GraphicCardType type);
//    ResponseEntity<?> getLaptopsByHardDrive(HardDriveType type, int capacity);
    
    // Comment
    ResponseEntity<?> getAllCommentsOfProduct(String productId);
    // Discount
    ResponseEntity<?> getDiscountsByProduct(String productId);
    // Feedback
    ResponseEntity<?> getAllFeedbacksOfProduct(String productId);
    ResponseEntity<?> getAllFeedbacksOfProductByRatingPoint(String productId, byte ratingPoint);
    // Images
    ResponseEntity<?> getProductImagesByProductId(String productId);
    ResponseEntity<?> getProductImagesByProductIdAndImageType(String productId, ImageType type);
}
