package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.model.ImportProduct;
import com.hcmute.ecom.service.ImportProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class ImportProductServiceImpl implements ImportProductService {
    @Override
    public ResponseEntity<?> insert(ImportProduct ticket) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(ImportProduct ticket) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long ticketId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllImportProductTicket() {
        return null;
    }

    @Override
    public ResponseEntity<?> findImportProductTicket(long ticketId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getImportProductTicketsByProductId(String productId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getImportProductTicketsByDate(LocalDate date) {
        return null;
    }

    @Override
    public ResponseEntity<?> getImportProductTicketsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }
}
