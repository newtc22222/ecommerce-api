package com.hcmute.ecom.service.impl;

import com.hcmute.ecom.model.laptop.Screen;
import com.hcmute.ecom.service.ScreenService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 * */
@Service
public class ScreenServiceImpl implements ScreenService {
    @Override
    public ResponseEntity<?> insert(Screen screen) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Screen screen) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(long screenId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllScreens() {
        return null;
    }

    @Override
    public ResponseEntity<?> findScreenById(long screenId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getScreenByConditions(Object... args) {
        return null;
    }
}
