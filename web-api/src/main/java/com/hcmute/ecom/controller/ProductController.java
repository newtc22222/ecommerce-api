package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.model.laptop.Laptop;
import com.hcmute.ecom.service.LaptopService;
import com.hcmute.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private LaptopService laptopService;

    @GetMapping("")
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
        return productService.getAllProduct();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String productId) {
        return productService.findProductById(productId);
    }

    @GetMapping("{id}/details")
    public ResponseEntity<?> getLaptopDetail(@PathVariable("id") String laptopId) {
        return laptopService.getLaptopDetail(laptopId);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewProduct(@RequestBody Laptop product) {
        return productService.insert(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAllForProduct(@PathVariable("id") String productId, @RequestBody Laptop product) {
        return productService.updateAll(product, productId);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updatePriceOfProduct(@PathVariable("id") String productId, @RequestBody ProductDTORequest productDTO) {
        return productService.updatePrice(productDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeProduct(@PathVariable("id") String productId) {
        return productService.delete(productId);
    }

    @PostMapping("/{id}/discount")
    public ResponseEntity<?> addDiscountToProduct(@PathVariable("id") String productId, @RequestBody long discountId) {
        return productService.insertDiscount(productId, discountId);
    }

    @DeleteMapping("/{id}/discount")
    public ResponseEntity<?> removeDiscountToProduct(@PathVariable("id") String productId, @RequestBody long discountId) {
        return productService.deleteDiscount(productId, discountId);
    }

    // Laptop
    @PostMapping("/{id}/graphic-cards")
    public ResponseEntity<?> addGraphicCardToLaptop(@PathVariable("id") String laptopId, @RequestBody long graphicCardId) {
        return laptopService.insertGraphicCard(laptopId, graphicCardId);
    }

    @DeleteMapping("/{id}/graphic-cards")
    public ResponseEntity<?> removeGraphicCardToLaptop(@PathVariable("id") String laptopId, @RequestBody long graphicCardId) {
        return laptopService.deleteGraphicCard(laptopId, graphicCardId);
    }

    @PostMapping("/{id}/hard-drives")
    public ResponseEntity<?> addHardDriveToLaptop(@PathVariable("id") String laptopId, @RequestBody long hardDriveId) {
        return laptopService.insertHardDrive(laptopId, hardDriveId);
    }

    @DeleteMapping("/{id}/hard-drives")
    public ResponseEntity<?> removeHardDriveToLaptop(@PathVariable("id") String laptopId, @RequestBody long hardDriveId) {
        return laptopService.deleteHardDrive(laptopId, hardDriveId);
    }
}
