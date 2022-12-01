package com.hcmute.ecom.dao;

import java.time.LocalDate;

/**
 * @author Nhat Phi
 * @since 2022-12-01
 * */
public interface ProductFiguresDAO {
    int getTotalQuantityProductSeller();
    int getTotalQuantityProductImport();
    int getTotalQuantityProductSellerInDate(LocalDate date);
    int getTotalQuantityProductImportInDate(LocalDate date);
    int getQuantityProductSellerByProductId(String productId);
    int getQuantityProductImportByProductId(String productId);
    int getQuantityProductSellerByProductIdInDate(String productId, LocalDate date);
    int getQuantityProductImportByProductIdInDate(String productId, LocalDate date);

    int getTotalProductTypesOfBrand(long brandId);
    int getTotalProductQuantityOfBrand(long brandId);
}
