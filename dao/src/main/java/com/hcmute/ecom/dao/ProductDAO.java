package com.hcmute.ecom.dao;

import com.hcmute.ecom.dto.request.LaptopDTORequest;
import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.laptop.Laptop;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface ProductDAO {
    int insert(Laptop product);
    int updateAll(Laptop product);
    int updatePrice(ProductDTORequest productDTO);
    int updateLaptopProperties(LaptopDTORequest laptopDTO);
    int delete(String productId);
    List<Laptop> getAllProduct();
    Laptop findProductById(String productId);

    // For product searching...
    List<Laptop> findProductsByName(String name);
    List<Laptop> getProductsByBrand(long brandId);
    List<Laptop> getProductsByCategory(long categoryId);
    List<Laptop> getProductsByReleasedYear(int year);
    List<Laptop> getProductsByPriceRange(BigDecimal startPrice, BigDecimal endPrice);

    // For laptop searching...
    List<Laptop> getLaptopsByRamCapacity(int ramCapacity);
    List<Laptop> getLaptopsByCPU(String cpuBrand, String cpuType);
    List<Laptop> getLaptopsByScreenSize(float screenSize);
    List<Laptop> getLaptopsByGraphicCardType(GraphicCardType type);
    List<Laptop> getLaptopsByHardDrive(HardDriveType type, int capacity);
}
