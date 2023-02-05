package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.dto.response.LaptopDTOResponse;
import com.hcmute.ecom.dto.response.LaptopDTOResponseCard;
import com.hcmute.ecom.model.laptop.Laptop;
import com.hcmute.ecom.service.LaptopService;
import com.hcmute.ecom.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Api(tags = "Product CRUD apis", value = "Product controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private LaptopService laptopService;

    @ApiOperation(value = "Get all products in system (without limit)", response = Laptop.class)
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

    @ApiOperation(value = "Get a product in system with id", response = Laptop.class)
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String productId) {
        return productService.findProductById(productId);
    }

    @ApiOperation(value = "Get all products of a brand in system", response = Laptop.class)
    @GetMapping("/brands/{brandId}/products")
    public ResponseEntity<?> getProductsByBrand(@PathVariable("brandId") long brandId) {
        return productService.getProductsByBrand(brandId);
    }

    @ApiOperation(value = "Get a product with full detail in system", response = LaptopDTOResponse.class)
    @GetMapping("/products/{id}/details")
    public ResponseEntity<?> getLaptopDetail(@PathVariable("id") String laptopId) {
        return laptopService.getLaptopDetail(laptopId);
    }

    @ApiOperation(value = "Get information for all product's cards", response = LaptopDTOResponseCard.class)
    @GetMapping("/products/cards")
    public ResponseEntity<?> getLaptopCards(@RequestParam(value = "name", required = false) String name,
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
            return laptopService.getLaptopCards();
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
        return laptopService.getLaptopCardsFilter(params);
    }

    @ApiOperation(value = "Get information for one product's card", response = LaptopDTOResponseCard.class)
    @GetMapping("/products/{productId}/cards")
    public ResponseEntity<?> getLaptopCard(@PathVariable("productId") String productId) {
        return laptopService.getLaptopCard(productId);
    }

    @ApiOperation(value = "Create a new product (laptop)", response = ResponseEntity.class)
    @PostMapping("/products")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createNewProduct(@RequestBody Laptop product) {
        return productService.insert(product);
    }

    @ApiOperation(value = "Update all information of product (laptop)", response = ResponseEntity.class)
    @PutMapping("/products/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateAllForProduct(@PathVariable("id") String productId,
                                                 @RequestBody Laptop product) {
        return productService.updateAll(product, productId);
    }

    @ApiOperation(value = "Update list price and price of product (laptop)", response = ResponseEntity.class)
    @PatchMapping("/products/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updatePriceOfProduct(@RequestBody ProductDTORequest productDTO) {
        return productService.updatePrice(productDTO);
    }

    @ApiOperation(value = "Remove product from system", response = ResponseEntity.class)
    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> removeProduct(@PathVariable("id") String productId) {
        return productService.delete(productId);
    }

    @ApiOperation(value = "Add a discount to product", response = ResponseEntity.class)
    @PostMapping("/products/{id}/discount")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> addDiscountToProduct(@PathVariable("id") String productId,
                                                  @RequestBody Map<String, Long> body) {
        return productService.insertDiscount(productId, body.get("discountId"));
    }

    @ApiOperation(value = "Remove a discount from product", response = ResponseEntity.class)
    @DeleteMapping("/products/{id}/discount")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> removeDiscountFromProduct(@PathVariable("id") String productId,
                                                     @RequestBody Map<String, Long> body) {
        return productService.deleteDiscount(productId, body.get("discountId"));
    }

    // Laptop
    @ApiOperation(value = "Add a graphic card to laptop", response = ResponseEntity.class)
    @PostMapping("/products/{id}/graphic-cards")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> addGraphicCardToLaptop(@PathVariable("id") String laptopId,
                                                    @RequestBody Map<String, Long> body) {
        return laptopService.insertGraphicCard(laptopId, body.get("graphicCardId"));
    }

    @ApiOperation(value = "Remove a graphic card from laptop", response = ResponseEntity.class)
    @DeleteMapping("/products/{id}/graphic-cards")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> removeGraphicCardFromLaptop(@PathVariable("id") String laptopId,
                                                       @RequestBody Map<String, Long> body) {
        return laptopService.deleteGraphicCard(laptopId, body.get("graphicCardId"));
    }

    @ApiOperation(value = "Add a hard drive to laptop", response = ResponseEntity.class)
    @PostMapping("/products/{id}/hard-drives")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> addHardDriveToLaptop(@PathVariable("id") String laptopId,
                                                  @RequestBody Map<String, Long> body) {
        return laptopService.insertHardDrive(laptopId, body.get("hardDriveId"));
    }

    @ApiOperation(value = "Remove a hard drive from laptop", response = ResponseEntity.class)
    @DeleteMapping("/products/{id}/hard-drives")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> removeHardDriveFromLaptop(@PathVariable("id") String laptopId,
                                                     @RequestBody Map<String, Long> body) {
        return laptopService.deleteHardDrive(laptopId, body.get("hardDriveId"));
    }
}
