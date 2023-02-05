package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.DiscountDTO;
import com.hcmute.ecom.model.Discount;
import com.hcmute.ecom.service.DiscountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Api(tags = "Discount code in system", value = "Discount controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @ApiOperation(value = "Get all discounts in system", response = Discount.class)
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

    @ApiOperation(value = "Get one discount with id", response = Discount.class)
    @GetMapping("/discounts/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> getDiscountById(@PathVariable("id") long discountId) {
        return discountService.getDiscountById(discountId);
    }

    @ApiOperation(value = "Get all discounts of product (with productId)", response = Discount.class)
    @GetMapping("/products/{productId}/discounts")
    public ResponseEntity<?> getDiscountsOfProduct(@PathVariable("productId") String productId) {
        return discountService.getDiscountsByProduct(productId);
    }

    @ApiOperation(value = "Create a new discount", response = ResponseEntity.class)
    @PostMapping("/discounts")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createNewDiscount(@RequestBody DiscountDTO discountDTO) {
        return discountService.insert(DiscountDTO.transform(discountDTO));
    }

    @ApiOperation(value = "Update a discount", response = ResponseEntity.class)
    @PutMapping("/discounts/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateDiscount(@PathVariable("id") long discountId,
                                            @RequestBody DiscountDTO discountDTO) {
        return discountService.update(DiscountDTO.transform(discountDTO), discountId);
    }

    @ApiOperation(value = "Remove a discount", response = ResponseEntity.class)
    @DeleteMapping("/discounts/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteDiscount(@PathVariable("id") long discountId) {
        return discountService.delete(discountId);
    }
}
