package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.Brand;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapper implements RowMapper<Brand> {
    @Override
    public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
        Brand brand = new Brand();
        brand.setId(rs.getLong("id"));
        brand.setName(rs.getString("name"));
        brand.setCountry(rs.getString("country"));
        brand.setEstablishDate(rs.getDate("establish_date"));
        brand.setLogo(rs.getString("logo"));
        return brand;
    }
}
