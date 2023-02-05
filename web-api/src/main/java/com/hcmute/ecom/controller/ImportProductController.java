package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.ImportProductDTO;
import com.hcmute.ecom.model.ImportProduct;
import com.hcmute.ecom.service.ImportProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Api(tags = "Ticket to import products", value = "Import Product controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/")
public class ImportProductController {
    @Autowired
    private ImportProductService importProductService;

    @ApiOperation(value = "Get all tickets", response = ImportProduct.class)
    @GetMapping("/imported")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getAllImportProductTickets(@RequestParam(value = "date", required = false) String date,
                                                        @RequestParam(value = "startDate", required = false) String startDate,
                                                        @RequestParam(value = "endDate", required = false) String endDate) {
        if (date != null) {
            return importProductService.getImportProductTicketsByDate(
                    LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (startDate != null && endDate != null) {
            return importProductService.getImportProductTicketsByDateRange(
                    LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            );
        }
        return importProductService.getAllImportProductTicket();
    }

    @ApiOperation(value = "Get a ticket with id", response = ImportProduct.class)
    @GetMapping("/imported/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getImportProductTicket(@PathVariable("id") long ticketId) {
        return importProductService.findImportProductTicket(ticketId);
    }

    @ApiOperation(value = "Get all tickets of a product", response = ImportProduct.class)
    @GetMapping("/products/{productId}/imported")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getImportProductTicketOfProduct(@PathVariable("productId") String productId,
                                                             @RequestParam(value = "date", required = false) String date,
                                                             @RequestParam(value = "startDate", required = false) String startDate,
                                                             @RequestParam(value = "endDate", required = false) String endDate) {
        //filter
        if(date != null) {
            return null;
        }
        if(startDate != null && endDate != null) {
            return null;
        }
        return importProductService.getImportProductTicketsByProductId(productId);
    }

    @ApiOperation(value = "Create a new ticket", response = ResponseEntity.class)
    @PostMapping("/imported")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createNewImportProductTicket(@RequestBody ImportProductDTO importProductDTO) {
        return importProductService.insert(ImportProductDTO.transform(importProductDTO));
    }

    @ApiOperation(value = "Update a ticket", response = ResponseEntity.class)
    @PutMapping("/imported/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateImportProductTicker(@PathVariable("id") long ticketId,
                                                       @RequestBody ImportProductDTO importProductDTO) {
        return importProductService.update(ImportProductDTO.transform(importProductDTO), ticketId);
    }

    @ApiOperation(value = "Delete a ticket", response = ResponseEntity.class)
    @DeleteMapping("/imported/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteImportProductTicket(@PathVariable("id") long ticketId) {
        return importProductService.delete(ticketId);
    }
}
