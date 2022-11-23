package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.model.Category;
import com.hcmute.ecom.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public ResponseEntity<?> insert(Category category) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Category category) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long categoryId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllCategory() {
        return null;
    }

    @Override
    public ResponseEntity<?> findCategoryById(long categoryId) {
        return null;
    }
}
