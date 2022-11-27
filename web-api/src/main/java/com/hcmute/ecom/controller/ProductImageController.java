package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.ProductImageDTO;
import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;

    @GetMapping("/images")
    public ResponseEntity<?> getAllImages(@RequestParam(value = "type", required = false) String type) {
        if(type != null) {
            return productImageService.getProductImagesByImageType(ImageType.valueOf(type));
        }
        return productImageService.getAllProductImages();
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageById(@PathVariable("id") String imageId) {
        return productImageService.findProductImageById(imageId);
    }

    @GetMapping("/products/{productId}/images")
    public ResponseEntity<?> getProductImagesByProductId(@PathVariable("productId") String productId,
                                                         @RequestParam(value = "type", required = false) String type) {
        if(type != null) {
            return productImageService.getProductImagesByProductIdAndImageType(productId, ImageType.valueOf(type));
        }
        return productImageService.getProductImagesByProductId(productId);
    }

    @PostMapping("/images")
    public ResponseEntity<?> createNewProductImage(@RequestParam Map<String, String> imageRequest) {
        return productImageService.insert(ProductImageDTO.transform(imageRequest));
    }

    @PutMapping("/images/{id}")
    public ResponseEntity<?> updateProductImage(@PathVariable("id") String imageId,
                                                @RequestParam Map<String, String> imageRequest) {
        return productImageService.update(ProductImageDTO.transform(imageRequest), imageId);
    }

    @PatchMapping("/images/{id}")
    public ResponseEntity<?> updateProductImagePathAndType(@PathVariable("id") String imageId,
                                                           @RequestParam Map<String, String> imageRequest) {
        String path = imageRequest.get("path");
        ImageType type = ImageType.valueOf(imageRequest.get("type"));
        return productImageService.updatePathAndType(imageId, path, type);
    }

    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteProductImage(@PathVariable("id") String imageId) {
        return productImageService.delete(imageId);
    }
}
