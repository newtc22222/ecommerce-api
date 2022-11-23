package com.hcmute.ecom.dao;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
public interface ProductHardDriveDAO {
    int insert(String productId, long hardDriveId);
    int delete(String productId, long hardDriveId);
}
