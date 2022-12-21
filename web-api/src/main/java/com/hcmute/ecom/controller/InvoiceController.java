package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.InvoiceDTO;
import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.model.Invoice;
import com.hcmute.ecom.service.InvoiceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Api(tags = "Receipt of user", value =  "Invoice Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @ApiOperation(value = "Get all invoices", response = Invoice.class)
    @GetMapping("/invoices")
    public ResponseEntity<?> getAllInvoices(@RequestParam(value = "address", required = false) String address,
                                            @RequestParam(value = "date", required = false) String date,
                                            @RequestParam(value = "startDate", required = false) String startDate,
                                            @RequestParam(value = "endDate", required = false) String endDate,
                                            @RequestParam(value = "paymentType", required = false) String paymentType,
                                            @RequestParam(value = "status", required = false) String status,
                                            @RequestParam(value = "isPaid", required = false) String isPaid) {
        if (address == null && date == null && startDate == null && endDate == null
                && paymentType == null && status == null && isPaid == null) {
            return invoiceService.getAllInvoices();
        }
        else {
            Map<String, String> params = new HashMap<>();
            if(address != null) params.put("address", address);
            if(date != null) params.put("date", date);
            if(startDate != null) params.put("startDate", startDate);
            if(endDate != null) params.put("endDate", endDate);
            if(paymentType != null) params.put("paymentType", paymentType);
            if(status != null) params.put("status", status);
            if(isPaid != null) params.put("isPaid", isPaid);
            return invoiceService.filter(params);
        }
    }

    @ApiOperation(value = "Get one invoice with id", response = Invoice.class)
    @GetMapping("/invoices/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable("id") String invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @ApiOperation(value = "Get all invoices of user", response = Invoice.class)
    @GetMapping("/users/{userId}/invoices")
    public ResponseEntity<?> getInvoicesOfUser(@PathVariable("userId") long userId) {
        return invoiceService.getInvoicesByUserId(userId);
    }

    @ApiOperation(value = "Create a new Invoice", response = ResponseEntity.class)
    @PostMapping("/invoices")
    public ResponseEntity<?> createNewInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.insert(InvoiceDTO.transform(invoiceDTO));
    }

    @ApiOperation(value = "Update all information of Invoice", response = ResponseEntity.class)
    @PutMapping("/invoices/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable("id") String invoiceId,
                                           @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.update(InvoiceDTO.transform(invoiceDTO), invoiceId);
    }

    @ApiOperation(value = "Update payment method and paid status of invoice", response = ResponseEntity.class)
    @PatchMapping("/invoices/{id}")
    public ResponseEntity<?> updateInvoicePaymentMethodAndPaidStatus(@PathVariable("id") String invoiceId,
                                                                     @RequestBody Map<String, String> request) {
        return invoiceService.updateInvoicePaymentMethodAndPaidStatus(
                invoiceId,
                request.get("paymentType"),
                Boolean.parseBoolean(request.get("isPaid")));
    }

    @ApiOperation(value = "Update the delivery status of Invoice", response = ResponseEntity.class)
    @PatchMapping("/invoices/{id}/status")
    public ResponseEntity<?> updateInvoiceStatus(@PathVariable("id") String invoiceId,
                                                 @RequestBody String status) {
        return invoiceService.updateStatus(invoiceId, OrderStatus.valueOf(status));
    }

    @ApiOperation(value = "Remove an invoice", response = ResponseEntity.class)
    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable("id") String invoiceId) {
        return invoiceService.delete(invoiceId);
    }
}
