package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.ProductImageDTO;
import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.ProductImage;
import com.hcmute.ecom.service.ProductImageService;
import com.hcmute.ecom.service.model.ResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Api(tags = "Image of product", value = "ProductImage controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;

    @ApiOperation(value = "Get all images in system", response = ProductImage.class)
    @GetMapping("/images")
    public ResponseEntity<?> getAllImages(@RequestParam(value = "type", required = false) String type) {
        if(type != null) {
            try {
                return productImageService.getProductImagesByImageType(ImageType.valueOf(type.toUpperCase()));
            }
            catch (Exception err) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject(
                                HttpStatus.BAD_REQUEST,
                                "Please check your \"type\" input!"
                        ));
            }
        }
        return productImageService.getAllProductImages();
    }

    @ApiOperation(value = "Get an image with id", response = ProductImage.class)
    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageById(@PathVariable("id") String imageId) {
        return productImageService.findProductImageById(imageId);
    }

    @ApiOperation(value = "Get all images of product", response = ProductImage.class)
    @GetMapping("/products/{productId}/images")
    public ResponseEntity<?> getProductImagesByProductId(@PathVariable("productId") String productId,
                                                         @RequestParam(value = "type", required = false) String type) {
        if(type != null) {
            try {
                return productImageService.getProductImagesByProductIdAndImageType(
                        productId,
                        ImageType.valueOf(type.toLowerCase(Locale.ROOT))
                );
            }
            catch (Exception err) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject(
                                HttpStatus.BAD_REQUEST,
                                "Please check your \"type\" input!"
                        ));
            }
        }
        return productImageService.getProductImagesByProductId(productId);
    }

    @ApiOperation(value = "Import a new image (link)", response = ResponseEntity.class)
    @PostMapping("/images")
    public ResponseEntity<?> createNewProductImage(@RequestBody ProductImageDTO imageDTO) {
        return productImageService.insert(ProductImageDTO.transform(imageDTO));
    }

    @ApiOperation(value = "Update all information of image", response = ResponseEntity.class)
    @PutMapping("/images/{id}")
    public ResponseEntity<?> updateProductImage(@PathVariable("id") String imageId,
                                                @RequestBody ProductImageDTO imageDTO) {
        return productImageService.update(ProductImageDTO.transform(imageDTO), imageId);
    }

    @ApiOperation(value = "Update path and type of image", response = ResponseEntity.class)
    @PatchMapping("/images/{id}")
    public ResponseEntity<?> updateProductImagePathAndType(@PathVariable("id") String imageId,
                                                           @RequestBody Map<String, String> imageRequest) {
        String path = imageRequest.get("path");
        ImageType type;
        try {
            type = ImageType.valueOf(imageRequest.get("type"));
        }
        catch (IllegalArgumentException err) {
            throw new RuntimeException(err);
        }
        return productImageService.updatePathAndType(imageId, path, type);
    }

    @ApiOperation(value = "Delete an image", response = ResponseEntity.class)
    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteProductImage(@PathVariable("id") String imageId) {
        return productImageService.delete(imageId);
    }
}
