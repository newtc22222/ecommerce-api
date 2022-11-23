package com.hcmute.ecom.dao;

import com.hcmute.ecom.model.laptop.Screen;

import java.util.List;

/**
 * @author Nhat Phi
 * @since 2022-11-21
 * */
public interface ScreenDAO {
    int insert(Screen screen);
    int update(Screen screen);
    int delete(long screenId);
    List<Screen> getAllScreens();
    Screen findScreenById(long screenId);
    List<Screen> getScreensBySize(float size);
    List<Screen> getScreensByResolution(String resolution);
    List<Screen> getScreensByType(String type);
    List<Screen> getScreensByPanel(String panel);
    List<Screen> getScreensByTouchScreenType(boolean hasTouchScreen);
}
