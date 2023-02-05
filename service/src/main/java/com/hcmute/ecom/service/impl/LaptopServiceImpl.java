package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.*;
import com.hcmute.ecom.dto.response.LaptopDTOResponse;
import com.hcmute.ecom.dto.response.LaptopDTOResponseCard;
import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.*;
import com.hcmute.ecom.model.laptop.GraphicCard;
import com.hcmute.ecom.model.laptop.HardDrive;
import com.hcmute.ecom.model.laptop.Laptop;
import com.hcmute.ecom.model.laptop.Screen;
import com.hcmute.ecom.service.LaptopService;
import com.hcmute.ecom.service.model.ResponseCUDObject;
import com.hcmute.ecom.service.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Nhat Phi
 * @since 2022-11-28
 * */
@Service
public class LaptopServiceImpl implements LaptopService {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private BrandDAO brandDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private DiscountDAO discountDAO;
    @Autowired
    private ProductImageDAO productImageDAO;
    @Autowired
    private GraphicCardDAO graphicCardDAO;
    @Autowired
    private ProductGraphicCardDAO productGraphicCardDAO;
    @Autowired
    private HardDriveDAO hardDriveDAO;
    @Autowired
    private ProductHardDriveDAO productHardDriveDAO;
    @Autowired
    private FeedbackDAO feedbackDAO;
    @Autowired
    private ScreenDAO screenDAO;

    @Override
    public ResponseEntity<?> getLaptopDetail(String laptopId) {
        Laptop laptop = productDAO.findProductById(laptopId);

        if(laptop == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + laptopId
                    ));
        }
        else {
            Brand brand = brandDAO.findBrandById(laptop.getBrandId());
            Category category = categoryDAO.findCategoryById(laptop.getCategoryId());
            Screen screen = screenDAO.findScreenById(laptop.getScreenId());
            List<GraphicCard> graphicCardList = graphicCardDAO.getGraphicCardByProductId(laptopId);
            List<HardDrive> hardDriveList = hardDriveDAO.getHardDriveByProductId(laptopId);
            return ResponseEntity
                    .ok(LaptopDTOResponse.getData(
                            laptop,
                            brand,
                            category,
                            graphicCardList,
                            hardDriveList,
                            screen
                    ));
        }
    }

    private LaptopDTOResponseCard getLaptopCardData(Laptop laptop) {
        String laptopId = laptop.getId();
        List<GraphicCard> graphicCardList = graphicCardDAO.getGraphicCardByProductId(laptopId);
        List<HardDrive> hardDriveList = hardDriveDAO.getHardDriveByProductId(laptopId);
        List<ProductImage> imagePathList = productImageDAO
                .getProductImagesByProductIdAndImageType(laptopId, ImageType.ADVERTISE);
        Screen screen = screenDAO.findScreenById(laptop.getScreenId());
        Discount discount = discountDAO.getDiscountOfProductInDate(laptopId);
        List<Feedback> feedbackList = feedbackDAO.getAllFeedbacksOfProduct(laptopId);
        float ratingPoint = 0;
        int feedbackCount = 0;
        if(feedbackList != null && feedbackList.size() > 0) {
            int sum = feedbackList.stream().map(feedback -> feedback.getRatingPoint().intValue())
                    .reduce(0, Integer::sum);
            feedbackCount = feedbackList.size();
            ratingPoint = sum * 1f / feedbackCount;
        }

        return LaptopDTOResponseCard.getData(
                laptop,
                (graphicCardList.size() > 0) ? graphicCardList.get(0) : null,
                (imagePathList.size() > 0) ? imagePathList.get(0).getPath() : "",
                (hardDriveList.size() > 0) ? hardDriveList.get(0) : null,
                screen,
                discount,
                ratingPoint,
                feedbackCount
        );
    }

    @Override
    public ResponseEntity<?> getLaptopCard(String laptopId) {
        Laptop laptop = productDAO.findProductById(laptopId);
        if(laptop == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find product with id = " + laptopId
                    ));
        }
        else {
            return ResponseEntity.ok(getLaptopCardData(laptop));
        }
    }

    @Override
    public ResponseEntity<?> getLaptopCards() {
        List<Laptop> laptopList = productDAO.getAllProduct();
        if(laptopList == null || laptopList.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find any product!"
                    ));
        }

        Stream<LaptopDTOResponseCard> laptopCardList = laptopList.stream().map(this::getLaptopCardData);

        return ResponseEntity.ok(laptopCardList);
    }

    private Set<Laptop> getLaptopFilter(Map<String, Object> params) {
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
    public ResponseEntity<?> getLaptopCardsFilter(Map<String, Object> params) {
        Set<Laptop> productSet = getLaptopFilter(params);
        if(productSet.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find product which suit this conditions!"
                    ));
        }

        Stream<LaptopDTOResponseCard> laptopCardSet = productSet.stream().map(this::getLaptopCardData);
        return ResponseEntity.ok(laptopCardSet);
    }

    @Override
    public ResponseEntity<?> insertGraphicCard(String laptopId, long graphicCardId) {
        return ResponseCUDObject.of(
                productGraphicCardDAO.insert(laptopId, graphicCardId) > 0,
                HttpStatus.CREATED,
                "Insert graphic card to laptop successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot insert graphic card to this laptop!"
        );
    }

    @Override
    public ResponseEntity<?> deleteGraphicCard(String laptopId, long graphicCardId) {
        return ResponseCUDObject.of(
                productGraphicCardDAO.delete(laptopId, graphicCardId) > 0,
                HttpStatus.OK,
                "Delete graphic card from laptop successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot delete graphic card from this laptop!"
        );
    }

    @Override
    public ResponseEntity<?> insertHardDrive(String laptopId, long hardDriveId) {
        return ResponseCUDObject.of(
                productHardDriveDAO.insert(laptopId, hardDriveId) > 0,
                HttpStatus.CREATED,
                "Insert hard drive to laptop successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot insert hard drive to this laptop!"
        );
    }

    @Override
    public ResponseEntity<?> deleteHardDrive(String laptopId, long hardDriveId) {
        return ResponseCUDObject.of(
                productHardDriveDAO.delete(laptopId, hardDriveId) > 0,
                HttpStatus.OK,
                "Delete hard drive from laptop successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Cannot delete hard drive from this laptop!"
        );
    }
}
