package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.model.ProductItem;

import java.math.BigDecimal;
import java.util.Map;

public class ProductItemDTO {
    public static ProductItem transform(Map<String, String> request) {
        ProductItem item = new ProductItem();
        if(request.containsKey("cartId")) {
            item.setCardId(request.get("cartId"));
        }
        if(request.containsKey("invoiceId")) {
            item.setInvoiceId(request.get("invoiceId"));
        }
        item.setProductId(request.get("productId"));
        item.setItemQuantity(Integer.parseInt(request.get("quantity")));
        item.setItemPrice(new BigDecimal(request.get("price")));
        item.setItemDiscountPrice(new BigDecimal(request.get("discountPrice")));
        return item;
    }
}
