package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.DiscountDTO;
import com.hcmute.ecom.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("/discounts")
    public ResponseEntity<?> getAllDiscount(@RequestParam(value = "code", required = false) String code,
                                            @RequestParam(value = "startDate", required = false) String startDate,
                                            @RequestParam(value = "endDate", required = false) String endDate,
                                            @RequestParam(value = "type", required = false) String type) {
        if(code == null && startDate == null && endDate == null && type == null) {
            return discountService.getAllDiscounts();
        }
        Map<String, String> params = new HashMap<>();
        return discountService.filter(params);
    }

    @GetMapping("/discounts/{id}")
    public ResponseEntity<?> getDiscountById(@PathVariable("id") long discountId) {
        return discountService.getDiscountById(discountId);
    }

    @PostMapping("/discounts")
    public ResponseEntity<?> createNewDiscount(@RequestBody Map<String, String> discountRequest) {
        return discountService.insert(DiscountDTO.transform(discountRequest));
    }

    @PutMapping("/discounts/{id}")
    public ResponseEntity<?> updateDiscount(@PathVariable("id") long discountId,
                                            @RequestBody Map<String, String> discountRequest) {
        return discountService.update(DiscountDTO.transform(discountRequest), discountId);
    }

    @DeleteMapping("/discounts/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable("id") long discountId) {
        return discountService.delete(discountId);
    }

    @GetMapping("/products/{productId}/discounts")
    public ResponseEntity<?> getDiscountsOfProduct(@PathVariable("productId") String productId) {
        return discountService.getDiscountsByProduct(productId);
    }
}
