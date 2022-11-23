package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.enums.DiscountType;
import com.hcmute.ecom.model.Discount;
import com.hcmute.ecom.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class DiscountServiceImpl implements DiscountService {
    @Override
    public ResponseEntity<?> insert(Discount discount) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Discount discount) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long discountId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllDiscounts() {
        return null;
    }

    @Override
    public ResponseEntity<?> findDiscountsByCode(String code) {
        return null;
    }

    @Override
    public ResponseEntity<?> getDiscountByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public ResponseEntity<?> getDiscountByType(DiscountType type) {
        return null;
    }
}
