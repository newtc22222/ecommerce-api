package com.hcmute.ecom.dto.request;

import com.hcmute.ecom.model.laptop.Screen;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-28
 * */
public class ScreenDTO {
    public static Screen transform(Map<String, String> request) {
        Screen screen = new Screen();
        if(request.containsKey("id")) {
            screen.setId(Long.parseLong(request.get("id")));
        }
        screen.setSize(Float.parseFloat(request.get("size")));
        screen.setTechnology(request.get("technology"));
        screen.setResolution(request.get("resolution"));
        screen.setType(request.get("type"));
        screen.setRefreshRate(Float.parseFloat("refreshRate"));
        screen.setPanel(request.get("panel"));
        if(request.containsKey("brightness")) {
            screen.setBrightness(Integer.parseInt(request.get("brightness")));
        }
        if(request.containsKey("colorCoverage")) {
            screen.setColorCoverage(request.get("colorCoverage"));
        }
        screen.setHasTouchScreen(Boolean.parseBoolean(request.get("hasTouchScreen")));
        return screen;
    }
}
