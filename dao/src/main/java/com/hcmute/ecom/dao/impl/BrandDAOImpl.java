package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.BrandDAO;
import com.hcmute.ecom.mapper.BrandMapper;
import com.hcmute.ecom.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-18
 * */
@Component
public class BrandDAOImpl implements BrandDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_brand";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s set name=?, country=?, establish_date=?, logo=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);

    @Override
    public int insert(Brand brand) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    brand.getName(),
                    brand.getCountry(),
                    brand.getEstablishDate(),
                    brand.getLogo()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(Brand brand) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    brand.getName(),
                    brand.getCountry(),
                    brand.getEstablishDate(),
                    brand.getLogo(),
                    brand.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long brandId) {
        return jdbcTemplate.update(DELETE, brandId);
    }

    @Override
    public List<Brand> getAllBrand() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new BrandMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Brand findBrandById(long brandId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new BrandMapper(),
                    brandId
            );
        } catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
