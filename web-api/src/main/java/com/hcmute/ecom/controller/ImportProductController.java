package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.ImportProductDTO;
import com.hcmute.ecom.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class ImportProductController {
    @Autowired
    private ImportProductService importProductService;

    @GetMapping("/imported")
    public ResponseEntity<?> getAllImportProductTickets(@RequestParam(value = "date", required = false) String date,
                                                        @RequestParam(value = "startDate", required = false) String startDate,
                                                        @RequestParam(value = "endDate", required = false) String endDate) {
        //filter
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

    @GetMapping("/imported/{id}")
    public ResponseEntity<?> getImportProductTicket(@PathVariable("id") long ticketId) {
        return importProductService.findImportProductTicket(ticketId);
    }

    @GetMapping("/products/{productId}/imported")
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

    @PostMapping("/imported")
    public ResponseEntity<?> createNewImportProductTicket(@RequestBody Map<String, String> importedRequest) {
        return importProductService.insert(ImportProductDTO.transform(importedRequest));
    }

    @PutMapping("/imported/{id}")
    public ResponseEntity<?> updateImportProductTicker(@PathVariable("id") long ticketId,
                                                       @RequestBody Map<String, String> importedRequest) {
        return importProductService.update(ImportProductDTO.transform(importedRequest), ticketId);
    }

    @DeleteMapping("/imported/{id}")
    public ResponseEntity<?> deleteImportProductTicket(@PathVariable("id") long ticketId) {
        return importProductService.delete(ticketId);
    }
}
