package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.ProductItemDTO;
import com.hcmute.ecom.model.ProductItem;
import com.hcmute.ecom.service.ProductItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Api(tags = "Items in cart or invoice", value = "ProductItem controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1")
public class ProductItemController {
    @Autowired
    private ProductItemService productItemService;

    @ApiOperation(value = "Get all items in cart", response = ProductItem.class)
    @GetMapping("/cart/{cartId}/items")
    public ResponseEntity<?> getProductItemsByCartId(@PathVariable("cartId") String cartId) {
        return productItemService.getProductItemsByCartId(cartId);
    }

    @ApiOperation(value = "Get all items in receipt", response = ProductItem.class)
    @GetMapping("/invoice/{invoiceId}/items")
    public ResponseEntity<?> getProductItemsByInvoiceId(@PathVariable("invoiceId") String invoiceId) {
        return productItemService.getProductItemsByInvoiceId(invoiceId);
    }

    @ApiOperation(value = "Get an item with id", response = ProductItem.class)
    @GetMapping("/items/{id}")
    public ResponseEntity<?> getProductItemsById(@PathVariable("id") String itemId) {
        return productItemService.findProductItemById(itemId);
    }

    @ApiOperation(value = "Add an item to cart", response = ResponseEntity.class)
    @PostMapping("/items")
    public ResponseEntity<?> insertNewItemToCart(@RequestBody ProductItemDTO itemDTO) {
        return productItemService.insert(ProductItemDTO.transform(itemDTO));
    }

    @ApiOperation(value = "Update an item's information", response = ResponseEntity.class)
    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateProductItem(@PathVariable("id") String itemId,
                                               @RequestBody ProductItemDTO itemDTO) {
        return productItemService.update(ProductItemDTO.transform(itemDTO), itemId);
    }

    @ApiOperation(value = "Update quantity, price and discount price of an item", response = ResponseEntity.class)
    @PatchMapping("/items/{id}")
    public ResponseEntity<?> updateProductItemProperties(@PathVariable("id") String itemId,
                                                         @RequestBody Map<String, String> itemRequest) {
        int quantity = Integer.getInteger(itemRequest.get("quantity"));
        BigDecimal price = new BigDecimal(itemRequest.get("price"));
        BigDecimal discountPrice = new BigDecimal(itemRequest.get("discountPrice"));
        return productItemService.updateProductItemProperties(itemId, quantity, price, discountPrice);
    }

    @ApiOperation(value = "Remove an item in cart (or invoice)", response = ResponseEntity.class)
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteProductItem(@PathVariable("id") String itemId) {
        return productItemService.delete(itemId);
    }
}
