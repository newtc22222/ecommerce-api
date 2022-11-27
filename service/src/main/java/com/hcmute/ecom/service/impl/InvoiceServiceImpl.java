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
import java.util.List;

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
    public ResponseEntity<?> updatePaymentType(String invoiceId, String paymentType) {
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
                invoiceDAO.updatePaymentType(invoiceId, paymentType) > 0,
                HttpStatus.OK,
                "Update payment's type of invoice successfully!",
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
        return ResponseCUDObject.of(
                invoiceDAO.delete(invoiceId) > 0,
                HttpStatus.OK,
                "Delete invoice successfully!",
                HttpStatus.NOT_FOUND,
                "Cannot find invoice with id = " + invoiceId
        );
    }

    @Override
    public ResponseEntity<?> getAllInvoices() {
        List<Invoice> invoices = invoiceDAO.getAllInvoices();
        if(invoices == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any invoices!"
                    ));
        }
        return ResponseEntity.ok(invoices);
    }

    @Override
    public ResponseEntity<?> getInvoiceById(String invoiceId) {
        Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);
        if(invoice == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find invoices with id = " + invoiceId
                    ));
        }
        return ResponseEntity.ok(invoice);
    }

    @Override
    public ResponseEntity<?> getInvoicesByUserId(long userId) {
        List<Invoice> invoices = invoiceDAO.getInvoicesByUserId(userId);
        if (invoices == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any invoices of this user!"
                    ));
        }
        return ResponseEntity.ok(invoices);
    }

    @Override
    public ResponseEntity<?> getInvoicesByAddress(String address) {
        List<Invoice> invoices = invoiceDAO.getInvoicesByAddress(address);
        if(invoices == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any invoices which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(invoices);
    }

    @Override
    public ResponseEntity<?> getInvoicesByDate(LocalDate date) {
        List<Invoice> invoices = invoiceDAO.getInvoicesByDate(date);
        if(invoices == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any invoices which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(invoices);
    }

    @Override
    public ResponseEntity<?> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Invoice> invoices = invoiceDAO.getInvoicesByDateRange(startDate, endDate);
        if(invoices == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any invoices which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(invoices);
    }

    @Override
    public ResponseEntity<?> getInvoicesByPaymentType(String paymentType) {
        List<Invoice> invoices = invoiceDAO.getInvoicesByPaymentType(paymentType);
        if(invoices == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any invoices which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(invoices);
    }

    @Override
    public ResponseEntity<?> getInvoicesByOrderStatus(OrderStatus status) {
        List<Invoice> invoices = invoiceDAO.getInvoicesByOrderStatus(status);
        if(invoices == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any invoices which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(invoices);
    }

    @Override
    public ResponseEntity<?> getInvoicesByPaidStatus(boolean isPaid) {
        List<Invoice> invoices = invoiceDAO.getInvoicesByPaidStatus(isPaid);
        if(invoices == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any invoices which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(invoices);
    }
}
