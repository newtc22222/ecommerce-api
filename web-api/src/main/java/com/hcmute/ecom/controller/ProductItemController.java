package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.ProductItemDTO;
import com.hcmute.ecom.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ProductItemController {
    @Autowired
    private ProductItemService productItemService;

    @GetMapping("/cart/{cartId}/items")
    public ResponseEntity<?> getProductItemsByCartId(@PathVariable("cartId") String cartId) {
        return productItemService.getProductItemsByCartId(cartId);
    }

    @GetMapping("/invoice/{invoiceId}/items")
    public ResponseEntity<?> getProductItemsByInvoiceId(@PathVariable("invoiceId") String invoiceId) {
        return productItemService.getProductItemsByInvoiceId(invoiceId);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<?> getProductItemsById(@PathVariable("id") String itemId) {
        return productItemService.findProductItemById(itemId);
    }

    @PostMapping("/items")
    public ResponseEntity<?> insertNewItemToCart(@RequestBody Map<String, String> itemRequest) {
        return productItemService.insert(ProductItemDTO.transform(itemRequest));
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateProductItem(@PathVariable("id") String itemId,
                                               @RequestBody Map<String, String> itemRequest) {
        return productItemService.update(ProductItemDTO.transform(itemRequest), itemId);
    }

    @PatchMapping("/items/{id}")
    public ResponseEntity<?> updateProductItemProperties(@PathVariable("id") String itemId,
                                                         @RequestBody Map<String, String> itemRequest) {
        int quantity = Integer.getInteger(itemRequest.get("quantity"));
        BigDecimal price = new BigDecimal(itemRequest.get("price"));
        BigDecimal discountPrice = new BigDecimal(itemRequest.get("discountPrice"));
        return productItemService.updateProductItemProperties(itemId, quantity, price, discountPrice);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteProductItem(@PathVariable("id") String itemId) {
        return productItemService.delete(itemId);
    }
}
