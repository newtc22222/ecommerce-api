package com.hcmute.ecom.dao;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
public interface ProductGraphicCardDAO {
    int insert(String productId, long graphicCardId);
    int delete(String productId, long graphicCardId);
}
