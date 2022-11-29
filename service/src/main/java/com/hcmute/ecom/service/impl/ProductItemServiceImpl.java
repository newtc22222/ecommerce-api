package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.ProductItemDAO;
import com.hcmute.ecom.model.ProductItem;
import com.hcmute.ecom.service.ProductItemService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class ProductItemServiceImpl implements ProductItemService {
    @Autowired
    private ProductItemDAO productItemDAO;

    @Override
    public ResponseEntity<?> insert(ProductItem productItem) {
        return ResponseCUDObject.of(
                productItemDAO.insert(productItem) > 0,
                HttpStatus.CREATED,
                "Insert new product item successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new product item! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(ProductItem productItem, String itemId) {
        ProductItem oldProductItem = productItemDAO.findProductItemById(itemId);
        if(oldProductItem == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product item with id = " + itemId
                    ));
        }
        else {
            oldProductItem.setCardId(productItem.getCardId());
            oldProductItem.setInvoiceId(productItem.getInvoiceId());
            oldProductItem.setProductId(productItem.getProductId());
            oldProductItem.setItemQuantity(productItem.getItemQuantity());
            oldProductItem.setItemPrice(productItem.getItemPrice());
            oldProductItem.setItemDiscountPrice(productItem.getItemDiscountPrice());
        }
        return ResponseCUDObject.of(
                productItemDAO.update(oldProductItem) > 0,
                HttpStatus.OK,
                "Update product item's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update product item! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updateProductItemProperties(String itemId, int quantity, BigDecimal price, BigDecimal discount_price) {
        ProductItem oldProductItem = productItemDAO.findProductItemById(itemId);
        if(oldProductItem == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product item with id = " + itemId
                    ));
        }
        return ResponseCUDObject.of(
                productItemDAO.updateProductItemProperties(itemId, quantity, price, discount_price) > 0,
                HttpStatus.OK,
                "Update product item's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update product item! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(String itemId) {
        if(productItemDAO.findProductItemById(itemId) == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product item with id = " + itemId
                    ));
        }
        return ResponseCUDObject.of(
                productItemDAO.delete(itemId) > 0,
                HttpStatus.OK,
                "Delete product item successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot delete product item with id = " + itemId
        );
    }

    @Override
    public ResponseEntity<?> findProductItemById(String itemId) {
        ProductItem productItem = productItemDAO.findProductItemById(itemId);
        if(productItem == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product item with id = " + itemId
                    ));
        }
        return ResponseEntity.ok(productItem);
    }

    @Override
    public ResponseEntity<?> getProductItemsByCartId(String cartId) {
        List<ProductItem> productItemList = productItemDAO.getProductItemsByCartId(cartId);
        if(productItemList == null || productItemList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any items which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(productItemList);
    }

    @Override
    public ResponseEntity<?> getProductItemsByInvoiceId(String invoiceId) {
        List<ProductItem> productItemList = productItemDAO.getProductItemsByInvoiceId(invoiceId);
        if(productItemList == null || productItemList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any items which suit this condition!"
                    ));
        }
        return ResponseEntity.ok(productItemList);
    }
}
