package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.InvoiceDAO;
import com.hcmute.ecom.enums.OrderStatus;
import com.hcmute.ecom.model.Invoice;
import com.hcmute.ecom.service.InvoiceService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceDAO invoiceDAO;

    @Override
    public ResponseEntity<?> insert(Invoice invoice) {
        return ResponseCUDObject.of(
                invoiceDAO.insert(invoice) > 0,
                HttpStatus.CREATED,
                "Insert new invoice successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new invoice! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(Invoice invoice, String invoiceId) {
        Invoice oldInvoice = invoiceDAO.getInvoiceById(invoiceId);
        if(oldInvoice == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find invoice with id = " + invoiceId
                    ));
        }
        else {
            oldInvoice.setStockQuantity(invoice.getStockQuantity());
            oldInvoice.setAddress(invoice.getAddress());
            oldInvoice.setShipFee(invoice.getShipFee());
            oldInvoice.setTotalCost(invoice.getTotalCost());
            oldInvoice.setDiscountId(invoice.getDiscountId());
            oldInvoice.setTax(invoice.getTax());
            oldInvoice.setFinalTotalCost(invoice.getFinalTotalCost());
            oldInvoice.setCreatedDate(invoice.getCreatedDate());
            oldInvoice.setPaymentType(invoice.getPaymentType());
            oldInvoice.setStatus(invoice.getStatus());
            oldInvoice.setPaid(invoice.getPaid());
            oldInvoice.setNote(invoice.getNote());
            oldInvoice.setTroubleReason(invoice.getTroubleReason());
        }
        return ResponseCUDObject.of(
                invoiceDAO.update(invoice) > 0,
                HttpStatus.OK,
                "Update invoice successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Fail when update this invoice! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updateStatus(String invoiceId, OrderStatus status) {
        Invoice oldInvoice = invoiceDAO.getInvoiceById(invoiceId);
        if(oldInvoice == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find invoice with id = " + invoiceId
                    ));
        }
        return ResponseCUDObject.of(
                invoiceDAO.updateStatus(invoiceId, status) > 0,
                HttpStatus.OK,
                "Update status of invoice successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Fail when update this invoice! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updateInvoicePaymentMethodAndPaidStatus(String invoiceId, String paymentType, boolean isPaid) {
        Invoice oldInvoice = invoiceDAO.getInvoiceById(invoiceId);
        if(oldInvoice == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find invoice with id = " + invoiceId
                    ));
        }
        return ResponseCUDObject.of(
                invoiceDAO.updateInvoicePaymentMethodAndPaidStatus(invoiceId, paymentType, isPaid) > 0,
                HttpStatus.OK,
                "Update payment type and paid status of invoice successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Fail when update this invoice! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updatePaidStatus(String invoiceId, boolean isPaid) {
        Invoice oldInvoice = invoiceDAO.getInvoiceById(invoiceId);
        if(oldInvoice == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find invoice with id = " + invoiceId
                    ));
        }
        return ResponseCUDObject.of(
                invoiceDAO.updatePaidStatus(invoiceId, isPaid) > 0,
                HttpStatus.OK,
                "Update paid status of invoice successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Fail when update this invoice! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(String invoiceId) {
        if(invoiceDAO.getInvoiceById(invoiceId) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find invoice with id = " + invoiceId
                    ));
        }
        return ResponseCUDObject.of(
                invoiceDAO.delete(invoiceId) > 0,
                HttpStatus.OK,
                "Delete invoice successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot delete invoice with id = " + invoiceId
        );
    }

    @Override
    public ResponseEntity<?> getAllInvoices() {
        List<Invoice> invoiceList = invoiceDAO.getAllInvoices();
        if(invoiceList == null || invoiceList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any invoiceList!"
                    ));
        }
        return ResponseEntity.ok(invoiceList);
    }

    @Override
    public ResponseEntity<?> getInvoiceById(String invoiceId) {
        Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);
        if(invoice == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find invoiceList with id = " + invoiceId
                    ));
        }
        return ResponseEntity.ok(invoice);
    }

    @Override
    public ResponseEntity<?> getInvoicesByUserId(long userId) {
        List<Invoice> invoiceList = invoiceDAO.getInvoicesByUserId(userId);
        if (invoiceList == null || invoiceList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any invoiceList of this user!"
                    ));
        }
        return ResponseEntity.ok(invoiceList);
    }

    @Override
    public ResponseEntity<?> filter(Map<String, String> params) {
        Set<Invoice> invoiceSet = new HashSet<>(invoiceDAO.getAllInvoices());
        Set<Invoice> notSuit = new HashSet<>();

        if (params.containsKey("address")) {
            List<Invoice> invoiceList = invoiceDAO.getInvoicesByAddress(params.get("address"));
            invoiceSet.forEach(invoice -> {
                if(!invoiceList.contains(invoice)) {
                    notSuit.add(invoice);
                }
            });
        }
        if (params.containsKey("date")) {
            List<Invoice> invoiceList = invoiceDAO.getInvoicesByDate(
                    LocalDate.parse(params.get("date"), DateTimeFormatter.ISO_LOCAL_DATE));
            invoiceSet.forEach(invoice -> {
                if(!invoiceList.contains(invoice)) {
                    notSuit.add(invoice);
                }
            });
        }
        if (params.containsKey("startDate") &&  params.containsKey("endDate")) {
            List<Invoice> invoiceList = invoiceDAO.getInvoicesByDateRange(
                    LocalDateTime.parse(params.get("startDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    LocalDateTime.parse(params.get("endDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );
            invoiceSet.forEach(invoice -> {
                if(!invoiceList.contains(invoice)) {
                    notSuit.add(invoice);
                }
            });
        }
        if (params.containsKey("paymentType")) {
            List<Invoice> invoiceList = invoiceDAO.getInvoicesByPaymentType(params.get("paymentType"));
            invoiceSet.forEach(invoice -> {
                if(!invoiceList.contains(invoice)) {
                    notSuit.add(invoice);
                }
            });
        }
        if (params.containsKey("status")) {
            List<Invoice> invoiceList = invoiceDAO.getInvoicesByOrderStatus(OrderStatus.valueOf(params.get("status")));
            invoiceSet.forEach(invoice -> {
                if(!invoiceList.contains(invoice)) {
                    notSuit.add(invoice);
                }
            });
        }
        if (params.containsKey("isPaid")) {
            List<Invoice> invoiceList = invoiceDAO.getInvoicesByPaidStatus(Boolean.parseBoolean(params.get("isPaid")));
            invoiceSet.forEach(invoice -> {
                if(!invoiceList.contains(invoice)) {
                    notSuit.add(invoice);
                }
            });
        }

        invoiceSet.removeAll(notSuit);
        if(invoiceSet.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find invoice which suit this conditions!"
                    ));
        }
        return ResponseEntity.ok(invoiceSet);
    }
}
