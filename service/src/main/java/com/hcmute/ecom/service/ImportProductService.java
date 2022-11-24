package com.hcmute.ecom.service;

import com.hcmute.ecom.model.ImportProduct;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface ImportProductService {
    ResponseEntity<?> insert(ImportProduct ticket);
    ResponseEntity<?> update(ImportProduct ticket, long ticketId);
    ResponseEntity<?> delete(long ticketId);
    ResponseEntity<?> getAllImportProductTicket();
    ResponseEntity<?> findImportProductTicket(long ticketId);
    ResponseEntity<?> getImportProductTicketsByProductId(String productId);
    ResponseEntity<?> getImportProductTicketsByDate(LocalDate date);
    ResponseEntity<?> getImportProductTicketsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
