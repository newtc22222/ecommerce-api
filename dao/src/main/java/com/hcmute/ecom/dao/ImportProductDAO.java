package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.ImportProduct;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface ImportProductDAO {
    int insert(ImportProduct ticket);
    int update(ImportProduct ticket);
    int delete(long ticketId);
    List<ImportProduct> getAllImportProductTicket();
    ImportProduct findImportProductTicket(long ticketId);
    List<ImportProduct> getImportProductTicketsByProductId(String productId);
    List<ImportProduct> getImportProductTicketsByDate(LocalDate date);
    List<ImportProduct> getImportProductTicketsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
//    List<ImportProduct> getImportProductTicketsByProductIdAndDate(String productId, LocalDate date);
//    List<ImportProduct> getImportProductTicketsByProductIdAndDateRange(String productId, LocalDateTime startDate, LocalDateTime endDate);
}
