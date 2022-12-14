package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.ProductDAO;
import com.hcmute.ecom.dao.ProductDiscountDAO;
import com.hcmute.ecom.dto.request.LaptopDTORequest;
import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.laptop.Laptop;
import com.hcmute.ecom.service.ProductService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ProductDiscountDAO productDiscountDAO;

    @Override
    public ResponseEntity<?> insert(Laptop product) {
        return ResponseCUDObject.of(
            productDAO.insert(product) > 0,
            HttpStatus.CREATED,
            "Create new product successfully!",
            HttpStatus.NOT_IMPLEMENTED,
            "Failed to create new product! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updateAll(Laptop product, String productId) {
        Laptop oldProduct = productDAO.findProductById(productId);
        if(oldProduct == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + productId
                    ));
        }
        else {
            oldProduct.setName(product.getName());
            oldProduct.setBrandId(product.getBrandId());
            oldProduct.setCategoryId(product.getCategoryId());
            oldProduct.setReleasedDate(product.getReleasedDate());
            oldProduct.setQuantityInStock(product.getQuantityInStock());
            oldProduct.setListedPrice(product.getListedPrice());
            oldProduct.setPrice(product.getPrice());

            oldProduct.setRamCapacity(product.getRamCapacity());
            oldProduct.setCpuBrand(product.getCpuBrand());
            oldProduct.setCpuType(product.getCpuType());
            oldProduct.setCpuMoreInformationHTML(product.getCpuMoreInformationHTML());
            oldProduct.setMoreDescriptionHTML(product.getMoreDescriptionHTML());
            oldProduct.setScreenId(product.getScreenId());
        }
        return ResponseCUDObject.of(
                productDAO.updateAll(product) > 0,
                HttpStatus.OK,
                "Update product successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update product! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updatePrice(ProductDTORequest productDTO) {
        Laptop oldProduct = productDAO.findProductById(productDTO.getId());
        if(oldProduct == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + productDTO.getId()
                    ));
        }
        return ResponseCUDObject.of(
                productDAO.updatePrice(productDTO) > 0,
                HttpStatus.OK,
                "Update price of product successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update price of product! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updateLaptopProperties(LaptopDTORequest laptopDTO) {
        Laptop oldProduct = productDAO.findProductById(laptopDTO.getId());
        if(oldProduct == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + laptopDTO.getId()
                    ));
        }
        return ResponseCUDObject.of(
                productDAO.updateLaptopProperties(laptopDTO) > 0,
                HttpStatus.OK,
                "Update laptop's properties successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update laptop's properties! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(String productId) {
        if(productDAO.findProductById(productId) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + productId
                    ));
        }
        return ResponseCUDObject.of(
                productDAO.delete(productId) > 0,
                HttpStatus.OK,
                "Delete product successfully",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to delete this product!"
        );
    }

    @Override
    public ResponseEntity<?> insertDiscount(String productId, long discountId) {
        return ResponseCUDObject.of(
                productDiscountDAO.insert(productId, discountId) > 0,
                HttpStatus.CREATED,
                "Insert discount to product successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot insert discount to this product!"
        );
    }

    @Override
    public ResponseEntity<?> deleteDiscount(String productId, long discountId) {
        return ResponseCUDObject.of(
                productDiscountDAO.delete(productId, discountId) > 0,
                HttpStatus.OK,
                "Delete discount from product successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot delete discount from this product!"
        );
    }

    @Override
    public ResponseEntity<?> getAllProduct() {
        List<Laptop> productList = productDAO.getAllProduct();
        if(productList == null || productList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any product!"
                    ));
        }
        return ResponseEntity.ok(productList);
    }

    @Override
    public ResponseEntity<?> findProductById(String productId) {
        Laptop product = productDAO.findProductById(productId);
        if(product == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + productId
                    ));
        }
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<?> getProductsByBrand(long brandId) {
        List<Laptop> productList = productDAO.getProductsByBrand(brandId);
        if(productList == null || productList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find any product of this brand!"
                    ));
        }
        return ResponseEntity.ok(productList);
    }

    private Set<Laptop> getProductFilter(Map<String, Object> params) {
        Set<Laptop> productSet = new HashSet<>(productDAO.getAllProduct());
        Set<Laptop> notSuit = new HashSet<>();

        if(params.containsKey("name")){
            List<Laptop> productList =
                    productDAO.findProductsByName(params.get("name").toString());
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("brandId")) {
            List<String> brandIdList = (List<String>) params.get("brandId");
            System.out.println(brandIdList);
            List<Laptop> productList = new ArrayList<>();
            brandIdList.forEach(brandId -> {
                System.out.println(brandId);
                List<Laptop> laptopList = productDAO.getProductsByBrand(Long.parseLong(brandId));
                productList.addAll(laptopList);
            });

            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("categoryId")) {
            List<Laptop> productList =
                    productDAO.getProductsByCategory(Long.parseLong(String.valueOf(params.get("categoryId"))));
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("releasedYear")) {
            List<Laptop> productList =
                    productDAO.getProductsByReleasedYear(Integer.parseInt(String.valueOf(params.get("releasedYear"))));
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("startPrice") && params.containsKey("endPrice")) {
            List<Laptop> productList = productDAO.getProductsByPriceRange(
                    new BigDecimal(String.valueOf(params.get("startPrice"))),
                    new BigDecimal(String.valueOf(params.get("endPrice")))
            );
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("ramCapacity")) {
            List<String> ramCapacityList = (List<String>) params.get("ramCapacity");
            List<Laptop> productList = new ArrayList<>();
            ramCapacityList.forEach(type -> {
                List<Laptop> laptopList = productDAO.getLaptopsByRamCapacity(Integer.parseInt(type));
                productList.addAll(laptopList);
            });

            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("cpuBrand") && params.containsKey("cpuType")) {
            List<Laptop> productList = productDAO.getLaptopsByCPU(
                    String.valueOf(params.get("cpuBrand")),
                    String.valueOf(params.get("cpuType"))
            );
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("screenSize")) {
            List<Laptop> productList =
                    productDAO.getLaptopsByScreenSize(Float.parseFloat(String.valueOf(params.get("screenSize"))));
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("graphicCardType")) {
            List<String> typeList = (List<String>) params.get("graphicCardType");
            List<Laptop> productList = new ArrayList<>();
            typeList.forEach(type -> {
                List<Laptop> laptopList = productDAO.getLaptopsByGraphicCardType(GraphicCardType.valueOf(type));
                productList.addAll(laptopList);
            });

            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("hardDriveType") && params.containsKey("capacity")) {
            List<String> typeList = (List<String>) params.get("hardDriveType");
            int capacity = Integer.parseInt(String.valueOf(params.get("capacity")));
            List<Laptop> productList = new ArrayList<>();
            typeList.forEach(type -> {
                List<Laptop> laptopList = productDAO.getLaptopsByHardDrive(HardDriveType.valueOf(type), capacity);
                productList.addAll(laptopList);
            });

            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }

        productSet.removeAll(notSuit);
        return productSet;
    }

    @Override
    public ResponseEntity<?> filter(Map<String, Object> params) {
        Set<Laptop> productSet = getProductFilter(params);
        if(productSet.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find product which suit this conditions!"
                    ));
        }
        return ResponseEntity.ok(productSet);
    }
}
