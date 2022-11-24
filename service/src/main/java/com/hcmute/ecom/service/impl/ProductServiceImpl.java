package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.ProductDAO;
import com.hcmute.ecom.dto.request.LaptopDTORequest;
import com.hcmute.ecom.dto.request.ProductDTORequest;
import com.hcmute.ecom.model.Product;
import com.hcmute.ecom.service.ProductService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public ResponseEntity<?> insert(Product product) {
        return ResponseCUDObject.of(
            productDAO.insert(product) > 0,
            HttpStatus.CREATED,
            "Create new product successfully!",
            HttpStatus.NOT_IMPLEMENTED,
            "Failed to create new product! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> updateAll(Product product, String productId) {
        Product oldProduct = productDAO.findProductById(productId);

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
        Product oldProduct = productDAO.findProductById(productDTO.getId());

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
        Product oldProduct = productDAO.findProductById(laptopDTO.getId());

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
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productDAO.getAllProduct();
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
        Product product = productDAO.findProductById(productId);
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
    public ResponseEntity<?> findProductsByConditions(Object... args) {
        return null;
    }
}
