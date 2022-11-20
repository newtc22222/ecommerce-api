package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.Category;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-20
 * */
public interface CategoryDAO {
    int insert(Category category);
    int update(Category category);
    int delete(long categoryId);
    List<Category> getAllCategory();
    Category findCategoryById(long categoryId);
}
