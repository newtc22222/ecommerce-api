package com.hcmute.ecom.controller;

import com.hcmute.ecom.dto.request.InvoiceDTO;
import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.service.InvoiceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @ApiOperation("Get all invoices")
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

    @ApiOperation("Get one invoice with id")
    @GetMapping("/invoices/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable("id") String invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @ApiOperation("Get all invoices of a user")
    @GetMapping("/users/{userId}/invoices")
    public ResponseEntity<?> getInvoicesOfUser(@PathVariable("userId") long userId) {
        return invoiceService.getInvoicesByUserId(userId);
    }

    @ApiOperation("Create a new Invoice")
    @PostMapping("/invoices")
    public ResponseEntity<?> createNewInvoice(@RequestBody Map<String, String> invoiceRequest) {
        return invoiceService.insert(InvoiceDTO.transform(invoiceRequest));
    }


    @ApiOperation("Update all value of an invoice")
    @PutMapping("/invoices/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable("id") String invoiceId,
                                           @RequestBody Map<String, String> invoiceRequest) {
        return invoiceService.update(InvoiceDTO.transform(invoiceRequest), invoiceId);
    }

    @ApiOperation("Update payment method and paid status of Invoice")
    @PatchMapping("/invoices/{id}")
    public ResponseEntity<?> updateInvoicePaymentMethodAndPaidStatus(@PathVariable("id") String invoiceId,
                                                                     @RequestBody Map<String, String> request) {
        return invoiceService.updateInvoicePaymentMethodAndPaidStatus(
                invoiceId,
                request.get("paymentType"),
                Boolean.parseBoolean(request.get("isPaid")));
    }

    @ApiOperation("Update the delivery status of Invoice")
    @PatchMapping("/invoices/{id}/status")
    public ResponseEntity<?> updateInvoiceStatus(@PathVariable("id") String invoiceId,
                                                 @RequestBody String status) {
        return invoiceService.updateStatus(invoiceId, OrderStatus.valueOf(status));
    }

    @ApiOperation("Remove an invoice in system")
    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable("id") String invoiceId) {
        return invoiceService.delete(invoiceId);
    }
}
