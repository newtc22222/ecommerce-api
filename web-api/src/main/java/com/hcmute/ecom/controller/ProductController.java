package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.model.laptop.Laptop;
import com.hcmute.ecom.service.LaptopService;
import com.hcmute.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private LaptopService laptopService;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProduct(@RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "brandId", required = false) List<String> brandId,
                                           @RequestParam(value = "categoryId", required = false) String categoryId,
                                           @RequestParam(value = "releasedYear", required = false) String releasedYear,
                                           @RequestParam(value = "startPrice", required = false) String startPrice,
                                           @RequestParam(value = "endPrice", required = false) String endPrice,
                                           @RequestParam(value = "ramCapacity", required = false) List<String> ramCapacity,
                                           @RequestParam(value = "cpuBrand", required = false) String cpuBrand,
                                           @RequestParam(value = "cpuType", required = false) String cpuType,
                                           @RequestParam(value = "screenSize", required = false) String screenSize,
                                           @RequestParam(value = "graphicCardType", required = false) List<String> graphicCardType,
                                           @RequestParam(value = "hardDriveType", required = false) List<String> hardDriveType,
                                           @RequestParam(value = "capacity", required = false) String capacity) {
        if(name == null && brandId == null && categoryId == null && releasedYear == null && startPrice == null
                && endPrice == null && ramCapacity == null && cpuBrand == null && cpuType == null && screenSize == null
                && graphicCardType == null && hardDriveType == null && capacity == null) {
            return productService.getAllProduct();
        }

        Map<String, Object> params = new HashMap<>();
        if(name != null) params.put("name", name);
        if(brandId != null) params.put("brandId", brandId);
        if(categoryId != null) params.put("categoryId", categoryId);
        if(releasedYear != null) params.put("releasedYear", releasedYear);
        if(startPrice != null) params.put("startPrice", startPrice);
        if(endPrice != null) params.put("endPrice", endPrice);
        if(ramCapacity != null) params.put("ramCapacity", ramCapacity);
        if(cpuBrand != null) params.put("cpuBrand", cpuBrand);
        if(cpuType != null) params.put("cpuType", cpuType);
        if(screenSize != null) params.put("screenSize", screenSize);
        if(graphicCardType != null) params.put("graphicCardType", graphicCardType);
        if(hardDriveType != null) params.put("hardDriveType", hardDriveType);
        if(capacity != null) params.put("capacity", capacity);
        return productService.filter(params);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String productId) {
        return productService.findProductById(productId);
    }

    @GetMapping("/brands/{brandId}/products")
    public ResponseEntity<?> getProductsByBrand(@PathVariable("brandId") long brandId) {
        return productService.getProductsByBrand(brandId);
    }

    @GetMapping("/products/{id}/details")
    public ResponseEntity<?> getLaptopDetail(@PathVariable("id") String laptopId) {
        return laptopService.getLaptopDetail(laptopId);
    }

    @GetMapping("/products/cards")
    public ResponseEntity<?> getLaptopCard() {
        return laptopService.getLaptopCards();
    }

    @PostMapping("/products")
    public ResponseEntity<?> createNewProduct(@RequestBody Laptop product) {
        return productService.insert(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateAllForProduct(@PathVariable("id") String productId, @RequestBody Laptop product) {
        return productService.updateAll(product, productId);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<?> updatePriceOfProduct(@PathVariable("id") String productId, @RequestBody ProductDTORequest productDTO) {
        return productService.updatePrice(productDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable("id") String productId) {
        return productService.delete(productId);
    }

    @PostMapping("/products/{id}/discount")
    public ResponseEntity<?> addDiscountToProduct(@PathVariable("id") String productId, @RequestBody long discountId) {
        return productService.insertDiscount(productId, discountId);
    }

    @DeleteMapping("/products/{id}/discount")
    public ResponseEntity<?> removeDiscountToProduct(@PathVariable("id") String productId, @RequestBody long discountId) {
        return productService.deleteDiscount(productId, discountId);
    }

    // Laptop
    @PostMapping("/products/{id}/graphic-cards")
    public ResponseEntity<?> addGraphicCardToLaptop(@PathVariable("id") String laptopId, @RequestBody long graphicCardId) {
        return laptopService.insertGraphicCard(laptopId, graphicCardId);
    }

    @DeleteMapping("/products/{id}/graphic-cards")
    public ResponseEntity<?> removeGraphicCardToLaptop(@PathVariable("id") String laptopId, @RequestBody long graphicCardId) {
        return laptopService.deleteGraphicCard(laptopId, graphicCardId);
    }

    @PostMapping("/products/{id}/hard-drives")
    public ResponseEntity<?> addHardDriveToLaptop(@PathVariable("id") String laptopId, @RequestBody long hardDriveId) {
        return laptopService.insertHardDrive(laptopId, hardDriveId);
    }

    @DeleteMapping("/products/{id}/hard-drives")
    public ResponseEntity<?> removeHardDriveToLaptop(@PathVariable("id") String laptopId, @RequestBody long hardDriveId) {
        return laptopService.deleteHardDrive(laptopId, hardDriveId);
    }
}
