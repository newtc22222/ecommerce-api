package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.CategoryDAO;
import com.hcmute.ecom.model.Category;
import com.hcmute.ecom.service.CategoryService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public ResponseEntity<?> insert(Category category) {
        return ResponseCUDObject.of(
                categoryDAO.insert(category) > 0,
                HttpStatus.CREATED,
                "Insert new category successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new category! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(Category category, long categoryId) {
        Category oldCategory = categoryDAO.findCategoryById(categoryId);

        if(oldCategory == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find category with id = " + categoryId
                    ));
        }
        else {
            oldCategory.setName(category.getName());
            oldCategory.setImage(category.getImage());
            oldCategory.setDescription(category.getDescription());
        }

        return ResponseCUDObject.of(
                categoryDAO.update(oldCategory) > 0,
                HttpStatus.OK,
                "Update category successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update category! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long categoryId) {
        if (categoryDAO.findCategoryById(categoryId) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find category with id = " + categoryId
                    ));
        }

        return ResponseCUDObject.of(
                categoryDAO.delete(categoryId) > 0,
                HttpStatus.OK,
                "Delete category successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to delete category with id = " + categoryId
        );
    }

    @Override
    public ResponseEntity<?> getAllCategory() {
        List<Category> categories = categoryDAO.getAllCategory();
        if (categories == null || categories.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any category!"
                    ));
        }
        return ResponseEntity.ok(categories);
    }

    @Override
    public ResponseEntity<?> findCategoryById(long categoryId) {
        Category category = categoryDAO.findCategoryById(categoryId);
        if(category == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find category with id = " + categoryId));
        }

        return ResponseEntity.ok(category);
    }
}
