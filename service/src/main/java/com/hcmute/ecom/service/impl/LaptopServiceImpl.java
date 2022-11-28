package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.*;
import com.hcmute.ecom.dto.response.LaptopDTOResponse;
import com.hcmute.ecom.model.Brand;
import com.hcmute.ecom.model.Category;
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
    private GraphicCardDAO graphicCardDAO;
    @Autowired
    private ProductGraphicCardDAO productGraphicCardDAO;
    @Autowired
    private HardDriveDAO hardDriveDAO;
    @Autowired
    private ProductHardDriveDAO productHardDriveDAO;
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
