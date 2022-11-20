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
    private final String QUERY_ALL = "select * from tbl_brand";
    private final String QUERY_ONE_BY_ID = "select * from tbl_brand where id = ? limit 1";
    private final String INSERT = "insert into tbl_brand values (0, ?, ?, ?, ?)";
    private final String UPDATE = "update tbl_brand set name=?, country=?, establish_date=?, logo=? where id = ?";
    private final String DELETE = "delete from tbl_brand where id=?";

    @Override
    public int insert(Brand brand) {
        return jdbcTemplate.update(
                INSERT,
                brand.getName(),
                brand.getCountry(),
                brand.getEstablishDate(),
                brand.getLogo()
        );
    }

    @Override
    public int update(Brand brand) {
        return jdbcTemplate.update(
                UPDATE,
                brand.getName(),
                brand.getCountry(),
                brand.getEstablishDate(),
                brand.getLogo(),
                brand.getId()
        );
    }

    @Override
    public int delete(long brandId) {
        return jdbcTemplate.update(DELETE, brandId);
    }

    @Override
    public List<Brand> getAllBrand() {
        List<Brand> brands = jdbcTemplate.query(
                QUERY_ALL,
                new BrandMapper());
        return brands;
    }

    @Override
    public Brand findBrandById(long brandId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new BrandMapper(), brandId
            );
        } catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
