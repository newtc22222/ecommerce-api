package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.ImportProductDAO;
import com.hcmute.ecom.model.ImportProduct;
import com.hcmute.ecom.service.ImportProductService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class ImportProductServiceImpl implements ImportProductService {
    @Autowired
    private ImportProductDAO importProductDAO;

    @Override
    public ResponseEntity<?> insert(ImportProduct importProduct) {
        return ResponseCUDObject.of(
                importProductDAO.insert(importProduct) > 0,
                HttpStatus.CREATED,
                "Insert new import product ticket successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new import product ticket! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(ImportProduct importProduct, long ticketId) {
        ImportProduct oldImportProduct = importProductDAO.findImportProductTicket(ticketId);

        if(oldImportProduct == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find import product ticket with ticket_id = " + ticketId
                    ));
        }
        else {
            oldImportProduct.setProductId(importProduct.getProductId());
            oldImportProduct.setQuantity(importProduct.getQuantity());
            oldImportProduct.setImportedDate(importProduct.getImportedDate());
            oldImportProduct.setImportedPrice(importProduct.getImportedPrice());
        }

        return ResponseCUDObject.of(
                importProductDAO.update(oldImportProduct) > 0,
                HttpStatus.OK,
                "Update import product ticket's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update import product ticket! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long ticketId) {
        return ResponseCUDObject.of(
                importProductDAO.delete(ticketId) > 0,
                HttpStatus.OK,
                "Delete import product ticket successfully!",
                HttpStatus.NOT_FOUND,
                "Cannot find import product with ticket_id = " + ticketId
        );
    }

    @Override
    public ResponseEntity<?> getAllImportProductTicket() {
        List<ImportProduct> importProductList = importProductDAO.getAllImportProductTicket();
        if(importProductList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any import product ticket!"
                    ));
        }
        return ResponseEntity.ok(importProductList);
    }

    @Override
    public ResponseEntity<?> findImportProductTicket(long ticketId) {
        ImportProduct importProduct = importProductDAO.findImportProductTicket(ticketId);
        if(importProduct == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find import product with ticket_id = " + ticketId
                    ));
        }
        return ResponseEntity.ok(importProduct);
    }

    @Override
    public ResponseEntity<?> getImportProductTicketsByProductId(String productId) {
        List<ImportProduct> importProductList = importProductDAO.getImportProductTicketsByProductId(productId);
        if(importProductList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any import product ticket which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(importProductList);
    }

    @Override
    public ResponseEntity<?> getImportProductTicketsByDate(LocalDate date) {
        List<ImportProduct> importProductList = importProductDAO.getImportProductTicketsByDate(date);
        if(importProductList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any import product ticket which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(importProductList);
    }

    @Override
    public ResponseEntity<?> getImportProductTicketsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<ImportProduct> importProductList = importProductDAO.getImportProductTicketsByDateRange(startDate, endDate);
        if(importProductList == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any import product ticket which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(importProductList);
    }
}
