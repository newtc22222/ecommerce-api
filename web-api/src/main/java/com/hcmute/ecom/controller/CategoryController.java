package com.hcmute.ecom.controller;

import com.hcmute.ecom.model.Category;
import com.hcmute.ecom.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Api(tags = "Type of Product", value = "Category controller")
@CrossOrigin(value = {"*"})
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Get all categories in system", response = Category.class)
    @GetMapping("")
    public ResponseEntity<?> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @ApiOperation(value = "Get one category in system", response = Category.class)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") long categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @ApiOperation(value = "Create a new category", response = ResponseEntity.class)
    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createNewCategory(@RequestBody Category category) {
        return categoryService.insert(category);
    }

    @ApiOperation(value = "Update a category", response = ResponseEntity.class)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateCategory(@PathVariable("id") long categoryId, @RequestBody Category category) {
        return categoryService.update(category, categoryId);
    }

    @ApiOperation(value = "Remove category", response = ResponseEntity.class)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") long categoryId) {
        return categoryService.delete(categoryId);
    }
}
