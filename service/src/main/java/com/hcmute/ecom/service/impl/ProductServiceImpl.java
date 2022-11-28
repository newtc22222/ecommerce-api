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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        List<Laptop> products = productDAO.getAllProduct();
        if(products == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any product!"
                    ));
        }
        return ResponseEntity.ok(products);
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
    public ResponseEntity<?> filter(Map<String, String> params) {
        Set<Laptop> productSet = new HashSet<>(productDAO.getAllProduct());
        Set<Laptop> notSuit = new HashSet<>();
        
        if(params.containsKey("name")){
            List<Laptop> productList = productDAO.findProductsByName(params.get("name"));
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("brandId")) {
            List<Laptop> productList = productDAO.getProductsByBrand(Long.parseLong(params.get("brandId")));
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("categoryId")) {
            List<Laptop> productList = productDAO.getProductsByCategory(Long.parseLong(params.get("categoryId")));
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("releasedYear")) {
            List<Laptop> productList = productDAO.getProductsByReleasedYear(Integer.parseInt(params.get("releasedYear")));
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("startPrice") && params.containsKey("endPrice")) {
            List<Laptop> productList = productDAO.getProductsByPriceRange(
                    new BigDecimal(params.get("startPrice")),
                    new BigDecimal(params.get("endPrice"))
            );
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("ramCapacity")) {
            List<Laptop> productList = productDAO.getLaptopsByRamCapacity(Integer.parseInt(params.get("ramCapacity")));
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("cpuBrand") && params.containsKey("cpuType")) {
            List<Laptop> productList = productDAO.getLaptopsByCPU(
                    params.get("cpuBrand"),
                    params.get("cpuType")
            );
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("screenSize")) {
            List<Laptop> productList = productDAO.getLaptopsByScreenSize(Float.parseFloat(params.get("screenSize")));
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("graphicCardType")) {
            List<Laptop> productList = productDAO.getLaptopsByGraphicCardType(
                    GraphicCardType.valueOf(params.get("graphicCardType")));
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }
        if(params.containsKey("hardDriveType") && params.containsKey("capacity")) {
            List<Laptop> productList = productDAO.getLaptopsByHardDrive(
                    HardDriveType.valueOf(params.get("hardDriveType")),
                    Integer.parseInt(params.get("capacity"))
            );
            productSet.forEach(product -> {
                if(!productList.contains(product)){
                    notSuit.add(product);
                }
            });
        }

        productSet.removeAll(notSuit);
        if(productSet.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find product which suit this conditions!"
                    ));
        }

        return ResponseEntity.ok(productSet);
    }
}
