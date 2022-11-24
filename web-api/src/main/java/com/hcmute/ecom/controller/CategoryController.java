package com.hcmute.ecom.controller;

import com.hcmute.ecom.model.Category;
import com.hcmute.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") long categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewCategory(@RequestBody Category category) {
        return categoryService.insert(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") long categoryId, @RequestBody Category category) {
        return categoryService.update(category, categoryId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") long categoryId) {
        return categoryService.delete(categoryId);
    }
}
