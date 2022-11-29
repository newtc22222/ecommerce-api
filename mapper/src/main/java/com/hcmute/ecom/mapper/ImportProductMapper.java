package com.hcmute.ecom.mapper;

import com.hcmute.ecom.model.ImportProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public class ImportProductMapper implements RowMapper<ImportProduct> {
    private static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public ImportProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImportProduct importProduct = new ImportProduct();
        importProduct.setTicketId(rs.getLong("ticket_id"));
        importProduct.setProductId(rs.getString("product_id"));
        importProduct.setQuantity(rs.getLong("quantity"));
        importProduct.setImportedPrice(rs.getBigDecimal("imported_price"));
        importProduct.setImportedDate(LocalDateTime.parse(rs.getString("imported_date"), DATE_TIME_PATTERN));
        return importProduct;
    }
}
