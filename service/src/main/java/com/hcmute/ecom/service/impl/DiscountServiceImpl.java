package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.DiscountDAO;
import com.hcmute.ecom.enums.DiscountType;
import com.hcmute.ecom.model.Discount;
import com.hcmute.ecom.service.DiscountService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountDAO discountDAO;

    @Override
    public ResponseEntity<?> insert(Discount discount) {
        return ResponseCUDObject.of(
                discountDAO.insert(discount) > 0,
                HttpStatus.CREATED,
                "Insert new discount successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new discount! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(Discount discount, long discountId) {
        Discount oldDiscount = discountDAO.findDiscountById(discountId);

        if(oldDiscount == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find discount with id = " + discountId
                    ));
        }
        else {
            oldDiscount.setCode(discount.getCode());
            oldDiscount.setRate(discount.getRate());
            oldDiscount.setDiscountType(discount.getDiscountType());
            oldDiscount.setMaxAmount(discount.getMaxAmount());
            oldDiscount.setAppliedDate(discount.getAppliedDate());
            oldDiscount.setEndedDate(discount.getEndedDate());
        }

        return ResponseCUDObject.of(
                discountDAO.update(oldDiscount) > 0,
                HttpStatus.OK,
                "Update discount's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update discount! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long discountId) {
        if(discountDAO.findDiscountById(discountId) == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find discount with id = " + discountId
                    ));
        }        
        return ResponseCUDObject.of(
                discountDAO.delete(discountId) > 0,
                HttpStatus.OK,
                "Delete discount successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot delete discount with id = " + discountId
        );
    }

    @Override
    public ResponseEntity<?> getAllDiscounts() {
        List<Discount> discountList = discountDAO.getAllDiscounts();
        if (discountList == null || discountList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any discount!"
                    ));
        }
        return ResponseEntity.ok(discountList);
    }

    @Override
    public ResponseEntity<?> getDiscountById(long discountId) {
        Discount discount = discountDAO.findDiscountById(discountId);
        if(discount == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find discount with id = " + discountId
                    ));
        }
        return ResponseEntity.ok(discount);
    }

    @Override
    public ResponseEntity<?> getDiscountsByProduct(String productId) {
        List<Discount> discountList = discountDAO.getDiscountsByProduct(productId);
        if(discountList == null || discountList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any discount in this product!"
                    ));
        }
        return ResponseEntity.ok(discountList);
    }

    @Override
    public ResponseEntity<?> filter(Map<String, String> params) {
        Set<Discount> discountSet = new HashSet<>(discountDAO.getAllDiscounts());
        Set<Discount> notSuit = new HashSet<>();

        if(params.containsKey("code")) {
            List<Discount> discountList = discountDAO.findDiscountsByCode(params.get("code"));
            discountSet.forEach(discount -> {
                if(!discountList.contains(discount)) {
                    notSuit.add(discount);
                }
            });
        }
        if(params.containsKey("startDate") && params.containsKey("endDate")) {
            List<Discount> discountList = discountDAO.getDiscountsByDateRange(
                    LocalDateTime.parse(params.get("startDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    LocalDateTime.parse(params.get("endDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );
            discountSet.forEach(discount -> {
                if(!discountList.contains(discount)) {
                    notSuit.add(discount);
                }
            });
        }
        if(params.containsKey("type")) {
            String type = params.get("type");
            if(type.equalsIgnoreCase(DiscountType.PRODUCT.toString())
                    || type.equalsIgnoreCase(DiscountType.PURCHASE.toString()))
            {
                List<Discount> discountList = discountDAO.getDiscountsByType(
                        DiscountType.valueOf(type.toUpperCase()));
                discountSet.forEach(discount -> {
                    if(!discountList.contains(discount)) {
                        notSuit.add(discount);
                    }
                });
            }
        }

        discountSet.removeAll(notSuit);
        if(discountSet.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find discount which suit this conditions!"
                    ));
        }
        return ResponseEntity.ok(discountSet);
    }
}
