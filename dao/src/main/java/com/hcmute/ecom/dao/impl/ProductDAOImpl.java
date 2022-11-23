package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.ProductDAO;
import com.hcmute.ecom.dto.request.LaptopDTORequest;
import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.mapper.ProductMapper;
import com.hcmute.ecom.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Component
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_category";
    private final String INSERT = String.format("insert into %s values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE_ALL = String.format("update %s " +
            "set name=?, brand_id=?, category_id=?, released_date=?, quantity_in_stock=?, listed_price=?, price=?, " +
            "ram_capacity=?, cpu_brand=?, cpu_type=?, cpu_more_infor_html=?, more_description_html=?, screen_id=? " +
            "where id=?", TABLE_NAME);
    private final String UPDATE_PRICE = String.format("update %s set listed_price=?, price=? where id=?", TABLE_NAME);
    private final String UPDATE_LAPTOP_PROPERTIES = String.format("update %s " +
            "set name=?, brand_id=?, released_date=?, ram_capacity=?, cpu_brand=?, cpu_type=?, " +
            "cpu_more_infor_html=?, more_description_html=?, screen_id=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);
    private final String QUERY_PRODUCTS_BY_NAME =
            String.format("select * from %s where name like ?", TABLE_NAME);
    private final String QUERY_PRODUCTS_BY_BRAND_ID =
            String.format("select * from %s where brand_id=?", TABLE_NAME);
    private final String QUERY_PRODUCTS_BY_CATEGORY_ID =
            String.format("select * from %s where category_id=?", TABLE_NAME);
    private final String QUERY_PRODUCTS_BY_RELEASED_YEAR =
            String.format("select * from %s where year(released_date)=?", TABLE_NAME);
    private final String QUERY_PRODUCTS_BY_PRICE_RANGE =
            String.format("select * from %s where price between ? and ?", TABLE_NAME);
    // Laptop
    private final String QUERY_LAPTOPS_BY_RAM_CAPACITY =
            String.format("select * from %s where ram_capacity=?", TABLE_NAME);
    private final String QUERY_LAPTOPS_BY_CPU =
            String.format("select * from %s where cpu_brand=? and cpu_type=?", TABLE_NAME);
    private final String QUERY_LAPTOPS_BY_SCREEN_SIZE =
            String.format("select * from %s p, tbl_screen s " +
                    "where p.screen_id = s.id and floor(s.size) = ?", TABLE_NAME);
    private final String QUERY_LAPTOPS_BY_GRAPHIC_CARD_TYPE =
            String.format("select * from %s where id in " +
                    "(SELECT pgc.product_id " +
                    " FROM tbl_product_graphic_card pgc, tbl_graphic_card gc" +
                    " WHERE pgc.graphic_card_id = gc.id AND gc.type = ?)", TABLE_NAME);
    private final String QUERY_LAPTOPS_BY_HARD_DRIVE =
            String.format("select * from %s where id in " +
                    "(SELECT phd.product_id " +
                    " FROM tbl_product_hard_drive phd, tbl_hard_drive hd" +
                    " WHERE phd.hard_drive_id = hd.id AND hd.type = ? AND hd.capacity = ?)", TABLE_NAME);

    @Override
    public int insert(Product product) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    product.getId(),
                    product.getName(),
                    product.getBrandId(),
                    product.getCategoryId(),
                    product.getReleasedDate(),
                    product.getQuantityInStock(),
                    product.getListedPrice(),
                    product.getPrice(),
                    product.getRamCapacity(),
                    product.getCpuBrand(),
                    product.getCpuType(),
                    product.getCpuMoreInformationHTML(),
                    product.getMoreDescriptionHTML(),
                    product.getScreenId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int updateAll(Product product) {
        try {
            return jdbcTemplate.update(
                    UPDATE_ALL,
                    product.getName(),
                    product.getBrandId(),
                    product.getCategoryId(),
                    product.getReleasedDate(),
                    product.getQuantityInStock(),
                    product.getListedPrice(),
                    product.getPrice(),
                    product.getRamCapacity(),
                    product.getCpuBrand(),
                    product.getCpuType(),
                    product.getCpuMoreInformationHTML(),
                    product.getMoreDescriptionHTML(),
                    product.getScreenId(),
                    product.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int updatePrice(ProductDTORequest productDTO) {
        try {
            return jdbcTemplate.update(
                    UPDATE_PRICE,
                    productDTO.getListed_price(),
                    productDTO.getPrice(),
                    productDTO.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int updateLaptopProperties(LaptopDTORequest laptopDTO) {
        try {
            return jdbcTemplate.update(
                    UPDATE_LAPTOP_PROPERTIES,
                    laptopDTO.getName(),
                    laptopDTO.getBrandId(),
                    laptopDTO.getReleasedDate(),
                    laptopDTO.getRamCapacity(),
                    laptopDTO.getCpuBrand(),
                    laptopDTO.getCpuType(),
                    laptopDTO.getCpuMoreInformationHTML(),
                    laptopDTO.getMoreDescriptionHTML(),
                    laptopDTO.getScreenId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(String productId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    productId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<Product> getAllProduct() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new ProductMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Product findProductById(String productId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new ProductMapper(),
                    productId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Product> findProductsByName(String name) {
        try {
            return jdbcTemplate.query(
                    QUERY_PRODUCTS_BY_NAME,
                    new ProductMapper(),
                    "%" + name + "%"
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Product> getProductsByBrand(long brandId) {
        try {
            return jdbcTemplate.query(
                    QUERY_PRODUCTS_BY_BRAND_ID,
                    new ProductMapper(),
                    brandId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Product> getProductsByCategory(long categoryId) {
        try {
            return jdbcTemplate.query(
                    QUERY_PRODUCTS_BY_CATEGORY_ID,
                    new ProductMapper(),
                    categoryId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Product> getProductsByReleasedYear(int year) {
        try {
            return jdbcTemplate.query(
                    QUERY_PRODUCTS_BY_RELEASED_YEAR,
                    new ProductMapper(),
                    year
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Product> getProductsByPriceRange(BigDecimal startPrice, BigDecimal endPrice) {
        try {
            return jdbcTemplate.query(
                    QUERY_PRODUCTS_BY_PRICE_RANGE,
                    new ProductMapper(),
                    startPrice.doubleValue(),
                    endPrice.doubleValue()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Product> getLaptopsByRamCapacity(int ramCapacity) {
        try {
            return jdbcTemplate.query(
                    QUERY_LAPTOPS_BY_RAM_CAPACITY,
                    new ProductMapper(),
                    ramCapacity
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Product> getLaptopsByCPU(String cpuBrand, String cpuType) {
        try {
            return jdbcTemplate.query(
                    QUERY_LAPTOPS_BY_CPU,
                    new ProductMapper(),
                    cpuBrand,
                    cpuType
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Product> getLaptopsByScreenSize(float screenSize) {
        try {
            return jdbcTemplate.query(
                    QUERY_LAPTOPS_BY_SCREEN_SIZE,
                    new ProductMapper(),
                    screenSize
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Product> getLaptopsByGraphicCardType(GraphicCardType type) {
        try {
            return jdbcTemplate.query(
                    QUERY_LAPTOPS_BY_GRAPHIC_CARD_TYPE,
                    new ProductMapper(),
                    type.toString()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Product> getLaptopsByHardDrive(HardDriveType type, int capacity) {
        try {
            return jdbcTemplate.query(
                    QUERY_LAPTOPS_BY_HARD_DRIVE,
                    new ProductMapper(),
                    type.toString(),
                    capacity
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
