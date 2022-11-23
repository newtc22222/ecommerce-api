package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.CategoryDAO;
import com.hcmute.ecom.mapper.CategoryMapper;
import com.hcmute.ecom.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
@Component
public class CategoryDAOImpl implements CategoryDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_category";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set name=?, image=?, description=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);

    @Override
    public int insert(Category category) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    category.getName(),
                    category.getImage(),
                    category.getDescription()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(Category category) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    category.getName(),
                    category.getImage(),
                    category.getDescription(),
                    category.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long categoryId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    categoryId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<Category> getAllCategory() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new CategoryMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Category findCategoryById(long categoryId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new CategoryMapper(),
                    categoryId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
