package com.hcmute.ecom.mapper;

import com.hcmute.ecom.enums.DiscountType;
import com.hcmute.ecom.model.Discount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class DiscountMapper implements RowMapper<Discount> {
    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public Discount mapRow(ResultSet rs, int rowNum) throws SQLException {
        Discount discount = new Discount();
        discount.setId(rs.getLong("id"));
        discount.setCode(rs.getString("code"));
        discount.setRate(rs.getFloat("rate"));
        discount.setDiscountType(DiscountType.valueOf(rs.getString("applied_type"))); // enum
        discount.setMaxAmount(rs.getBigDecimal("max_amount"));
        discount.setAppliedDate(LocalDateTime.parse(rs.getString("applied_date"), DATE_TIME_PATTERN));
        discount.setEndedDate(LocalDateTime.parse(rs.getString("ended_date"), DATE_TIME_PATTERN));
        return discount;
    }
}
