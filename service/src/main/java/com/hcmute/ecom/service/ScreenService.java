package com.hcmute.ecom.service;

import com.hcmute.ecom.model.laptop.Screen;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-22
 */
public interface ScreenService {
    ResponseEntity<?> insert(Screen screen);
    ResponseEntity<?> update(Screen screen, long screenId);
    ResponseEntity<?> delete(long screenId);

    ResponseEntity<?> getAllScreens();
    ResponseEntity<?> findScreenById(long screenId);
    ResponseEntity<?> filter(Map<String, String> params);
}
