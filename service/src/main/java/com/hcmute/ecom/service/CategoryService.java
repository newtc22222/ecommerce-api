package com.hcmute.ecom.service;

import com.hcmute.ecom.model.Category;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface CategoryService {
    ResponseEntity<?> insert(Category category);
    ResponseEntity<?> update(Category category, long categoryId);
    ResponseEntity<?> delete(long categoryId);
    ResponseEntity<?> getAllCategory();
    ResponseEntity<?> findCategoryById(long categoryId);
}
