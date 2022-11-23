package com.hcmute.ecom.dao.impl;

import com.hcmute.ecom.dao.ImportProductDAO;
import com.hcmute.ecom.mapper.ImportProductMapper;
import com.hcmute.ecom.model.ImportProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
@Component
public class ImportProductDAOImpl implements ImportProductDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query String
    private final String TABLE_NAME = "tbl_import_product";
    private final String INSERT = String.format("insert into %s values (0, ?, ?, ?, ?)", TABLE_NAME);
    private final String UPDATE = String.format("update %s " +
            "set product_id=?, quantity=?, imported_price=?, imported_date=? where ticket_id=?", TABLE_NAME);
    private final String DELETE = String.format("delete from %s where ticket_id=?", TABLE_NAME);

    private final String QUERY_ALL = String.format("select * from %s", TABLE_NAME);
    private final String QUERY_ONE_BY_ID = String.format("select * from %s where ticket_id=? limit 1", TABLE_NAME);
    private final String QUERY_IMPORT_PRODUCT_TICKETS_BY_PRODUCT_ID =
            String.format("select * from %s where product_id=?", TABLE_NAME);
    private final String QUERY_IMPORT_PRODUCT_TICKETS_BY_DATE=
            String.format("select * from %s where cast(imported_date as date) = ?", TABLE_NAME);
    private final String QUERY_IMPORT_PRODUCT_TICKETS_BY_DATE_RANGE =
            String.format("select * from %s where imported_date between ? and ?", TABLE_NAME);

    @Override
    public int insert(ImportProduct ticket) {
        try {
            return jdbcTemplate.update(
                    INSERT,
                    ticket.getProductId(),
                    ticket.getQuantity(),
                    ticket.getImportedPrice().doubleValue(),
                    Timestamp.valueOf(ticket.getImportedDate())
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int update(ImportProduct ticket) {
        try {
            return jdbcTemplate.update(
                    UPDATE,
                    ticket.getProductId(),
                    ticket.getQuantity(),
                    ticket.getImportedPrice().doubleValue(),
                    Timestamp.valueOf(ticket.getImportedDate()),
                    ticket.getTicketId()
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public int delete(long ticketId) {
        try {
            return jdbcTemplate.update(
                    DELETE,
                    ticketId
            );
        }
        catch (Exception err) {
            return 0;
        }
    }

    @Override
    public List<ImportProduct> getAllImportProductTicket() {
        try {
            return jdbcTemplate.query(
                    QUERY_ALL,
                    new ImportProductMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public ImportProduct findImportProductTicket(long ticketId) {
        try {
            return jdbcTemplate.queryForObject(
                    QUERY_ONE_BY_ID,
                    new ImportProductMapper()
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<ImportProduct> getImportProductTicketsByProductId(String productId) {
        try {
            return jdbcTemplate.query(
                    QUERY_IMPORT_PRODUCT_TICKETS_BY_PRODUCT_ID,
                    new ImportProductMapper(),
                    productId
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<ImportProduct> getImportProductTicketsByDate(Date date) {
        try {
            return jdbcTemplate.query(
                    QUERY_IMPORT_PRODUCT_TICKETS_BY_DATE,
                    new ImportProductMapper(),
                    date
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public List<ImportProduct> getImportProductTicketsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        try {
            return jdbcTemplate.query(
                    QUERY_IMPORT_PRODUCT_TICKETS_BY_DATE_RANGE,
                    new ImportProductMapper(),
                    startDate,
                    endDate
            );
        }
        catch (EmptyResultDataAccessException err) {
            return null;
        }
    }
}
