package com.hcmute.ecom.dao;

import com.hcmute.ecom.dto.request.LaptopDTORequest;
import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface ProductDAO {
    int insert(Product product);
    int updateAll(Product product);
    int updatePrice(ProductDTORequest productDTO);
    int updateLaptopProperties(LaptopDTORequest laptopDTO);
    int delete(String productId);
    List<Product> getAllProduct();
    Product findProductById(String productId);

    // For product searching...
    List<Product> findProductsByName(String name);
    List<Product> getProductsByBrand(long brandId);
    List<Product> getProductsByCategory(long categoryId);
    List<Product> getProductsByReleasedYear(int year);
    List<Product> getProductsByPriceRange(BigDecimal startPrice, BigDecimal endPrice);

    // For laptop searching...
    List<Product> getLaptopsByRamCapacity(int ramCapacity);
    List<Product> getLaptopsByCPU(String cpuBrand, String cpuType);
    List<Product> getLaptopsByScreenSize(float screenSize);
    List<Product> getLaptopsByGraphicCardType(GraphicCardType type);
    List<Product> getLaptopsByHardDrive(HardDriveType type, int capacity);
}
