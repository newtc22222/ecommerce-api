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
import java.util.List;

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
        return ResponseCUDObject.of(
                discountDAO.delete(discountId) > 0,
                HttpStatus.OK,
                "Delete discount successfully!",
                HttpStatus.NOT_FOUND,
                "Cannot find discount with id = " + discountId
        );
    }

    @Override
    public ResponseEntity<?> getAllDiscounts() {
        List<Discount> discountList = discountDAO.getAllDiscounts();
        if (discountList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any discount!"
                    ));
        }
        return ResponseEntity.ok(discountList);
    }

    @Override
    public ResponseEntity<?> findDiscountsByCode(String code) {
        List<Discount> discountList = discountDAO.findDiscountsByCode(code);

        if(discountList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any discount which suit this condition!"
                    ));
        }

        return ResponseEntity.ok(discountList);
    }

    @Override
    public ResponseEntity<?> getDiscountByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Discount> discountList = discountDAO.getDiscountsByDateRange(startDate, endDate);

        if(discountList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any discount which suit this condition!"
                    ));
        }

        return ResponseEntity.ok(discountList);
    }

    @Override
    public ResponseEntity<?> getDiscountByType(DiscountType type) {
        List<Discount> discountList = discountDAO.getDiscountsByType(type);

        if(discountList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any discount which suit this condition!"
                    ));
        }

        return ResponseEntity.ok(discountList);
    }
}
