package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.*;
import com.hcmute.ecom.dto.response.LaptopDTOResponse;
import com.hcmute.ecom.dto.response.LaptopDTOResponseCard;
import com.hcmute.ecom.enums.ImageType;
import com.hcmute.ecom.model.Brand;
import com.hcmute.ecom.model.Category;
import com.hcmute.ecom.model.Discount;
import com.hcmute.ecom.model.Feedback;
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

import java.util.List;
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
        String imagePath = productImageDAO
                .getProductImagesByProductIdAndImageType(laptopId, ImageType.ADVERTISE).get(0).getPath();
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
                (graphicCardList != null) ? graphicCardList.get(0) : null,
                imagePath,
                (hardDriveList != null) ? hardDriveList.get(0) : null,
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
