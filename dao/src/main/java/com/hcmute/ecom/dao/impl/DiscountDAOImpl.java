package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.DiscountDAO;
import com.hcmute.ecom.enums.DiscountType;
import com.hcmute.ecom.mapper.DiscountMapper;
import com.hcmute.ecom.model.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
@Component
public class DiscountDAOImpl implements DiscountDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_discount";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set code=?, rate=?, applied_type=?, max_amount=?, applied_date=?, ended_date=? where id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where id=? limit 1", TABLE_NAME);
    private final String QUERY_DISCOUNTS_BY_CODE =
            String.format("select * from %s where code like ?", TABLE_NAME);
    /**
     * Query in another table (tbl_product_discount)
     * */
    private final String QUERY_DISCOUNTS_BY_PRODUCT_ID =
            String.format("select d.* " +
                    "from %s d, tbl_product_discount pd " +
                    "where d.id = pd.discount_id " +
                    "and pd.product_id = ?", TABLE_NAME);

    private final String QUERY_DISCOUNT_OF_PRODUCT_IN_DATE =
            String.format("select d.* " +
                    "from %s d, tbl_product_discount pd " +
                    "where d.id = pd.discount_id " +
                    "and pd.product_id = ? " +
                    "and ? between d.applied_date and d.ended_date " +
                    "limit 1;", TABLE_NAME);
    private final String QUERY_DISCOUNTS_BY_DATE_RANGE =
            String.format("select * from %s where ended_date >= ? or applied_date <= ?", TABLE_NAME);
    private final String QUERY_DISCOUNTS_BY_TYPE =
            String.format("select * from %s where applied_type=?", TABLE_NAME);

    @Override
    public int insert(Discount discount) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    discount.getCode(),
                    discount.getRate(),
                    discount.getDiscountType().toString(),
                    discount.getMaxAmount().doubleValue(),
                    Timestamp.valueOf(discount.getAppliedDate()),
                    Timestamp.valueOf(discount.getEndedDate())
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(Discount discount) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    discount.getCode(),
                    discount.getRate(),
                    discount.getDiscountType().toString(),
                    discount.getMaxAmount().doubleValue(),
                    Timestamp.valueOf(discount.getAppliedDate()),
                    Timestamp.valueOf(discount.getEndedDate()),
                    discount.getId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long discountId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    discountId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<Discount> getAllDiscounts() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new DiscountMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Discount findDiscountById(long discountId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new DiscountMapper(),
                    discountId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Discount> findDiscountsByCode(String code) {
        try {
            return jdbcTemplate.query(
                    QUERY_DISCOUNTS_BY_CODE,
                    new DiscountMapper(),
                    "%" + code + "%"
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Discount> getDiscountsByProduct(String productId) {
        try {
            return jdbcTemplate.query(
                    QUERY_DISCOUNTS_BY_PRODUCT_ID,
                    new DiscountMapper(),
                    productId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public Discount getDiscountOfProductInDate(String productId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_DISCOUNT_OF_PRODUCT_IN_DATE,
                    new DiscountMapper(),
                    productId,
                    Date.valueOf(LocalDate.now())
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Discount> getDiscountsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        try {
            return jdbcTemplate.query(
                    QUERY_DISCOUNTS_BY_DATE_RANGE,
                    new DiscountMapper(),
                    Timestamp.valueOf(startDate),
                    Timestamp.valueOf(endDate)
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<Discount> getDiscountsByType(DiscountType type) {
        try {
            return jdbcTemplate.query(
                    QUERY_DISCOUNTS_BY_TYPE,
                    new DiscountMapper(),
                    type.toString()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
