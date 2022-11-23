package com.hcmute.ecom.service;

import com.hcmute.ecom.model.laptop.Screen;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface ScreenService {
    ResponseEntity<?> insert(Screen screen);
    ResponseEntity<?> update(Screen screen);
    ResponseEntity<?> delete(long screenId);

    ResponseEntity<?> getAllScreens();
    ResponseEntity<?> findScreenById(long screenId);
    ResponseEntity<?> getScreenByConditions(Object ...args);

//    ResponseEntity<?> getScreensBySize(float size);
//    ResponseEntity<?> getScreensByResolution(String resolution);
//    ResponseEntity<?> getScreensByType(String type);
//    ResponseEntity<?> getScreensByPanel(String panel);
//    ResponseEntity<?> getScreensByTouchScreenType(boolean hasTouchScreen);
}
