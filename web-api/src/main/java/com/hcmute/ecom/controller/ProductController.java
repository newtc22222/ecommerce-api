package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.model.Product;
import com.hcmute.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String productId) {
        return productService.findProductById(productId);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        return productService.insert(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAllForProduct(@PathVariable("id") String productId, @RequestBody Product product) {
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
}
