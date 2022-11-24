package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.dao.ScreenDAO;
import com.hcmute.ecom.model.laptop.Screen;
import com.hcmute.ecom.service.ScreenService;
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
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenDAO screenDAO;

    @Override
    public ResponseEntity<?> insert(Screen screen) {
        return ResponseCUDObject.of(
                screenDAO.insert(screen) > 0,
                HttpStatus.CREATED,
                "Insert new screen successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to insert new screen! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> update(Screen screen, long screenId) {
        Screen oldScreen = screenDAO.findScreenById(screenId);

        if(oldScreen == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find screen with id = " + screenId
                    ));
        }
        else {
            oldScreen.setSize(screen.getSize());
            oldScreen.setTechnology(screen.getTechnology());
            oldScreen.setResolution(screen.getResolution());
            oldScreen.setType(screen.getType());
            oldScreen.setRefreshRate(screen.getRefreshRate());
            oldScreen.setPanel(screen.getPanel());
            oldScreen.setBrightness(screen.getBrightness());
            oldScreen.setColorCoverage(screen.getColorCoverage());
            oldScreen.setHasTouchScreen(screen.getHasTouchScreen());
        }

        return ResponseCUDObject.of(
                screenDAO.update(oldScreen) > 0,
                HttpStatus.OK,
                "Update screen's information successfully!",
                HttpStatus.NOT_IMPLEMENTED,
                "Failed to update screen! Please check your data again!"
        );
    }

    @Override
    public ResponseEntity<?> delete(long screenId) {
        return ResponseCUDObject.of(
                screenDAO.delete(screenId) > 0,
                HttpStatus.OK,
                "Delete screen successfully!",
                HttpStatus.NOT_FOUND,
                "Cannot find screen with id = " + screenId
        );
    }

    @Override
    public ResponseEntity<?> getAllScreens() {
        List<Screen> screens = screenDAO.getAllScreens();
        if (screens == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            HttpStatus.NO_CONTENT,
                            "Cannot find any screen!"
                    ));
        }
        return ResponseEntity.ok(screens);
    }

    @Override
    public ResponseEntity<?> findScreenById(long screenId) {
        Screen screen = screenDAO.findScreenById(screenId);
        if(screen == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find screen with id = " + screenId
                    ));
        }
        return ResponseEntity.ok(screen);
    }

    @Override
    public ResponseEntity<?> getScreenByConditions(Object... args) {
        return null;
    }
}
