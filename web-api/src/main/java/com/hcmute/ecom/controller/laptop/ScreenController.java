package com.hcmute.ecom.controller.laptop;

import com.hcmute.ecom.dto.request.ScreenDTO;
import com.hcmute.ecom.model.laptop.Screen;
import com.hcmute.ecom.service.ScreenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/screens")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @ApiOperation("Get all screens in system")
    @GetMapping("")
    public ResponseEntity<?> getAllScreens(@RequestParam(value = "size", required = false) String size,
                                           @RequestParam(value = "resolution", required = false) String resolution,
                                           @RequestParam(value = "type", required = false) String type,
                                           @RequestParam(value = "panel", required = false) String panel,
                                           @RequestParam(value = "hasTouchScreen", required = false) String hasTouchScreen) {
        if (size == null && resolution == null && type == null && panel == null && hasTouchScreen == null) {
            return screenService.getAllScreens();
        }
        else {
            Map<String, String> params = new HashMap<>();
            if(size != null) params.put("size", size);
            if(resolution != null) params.put("resolution", resolution);
            if(type != null) params.put("type", type);
            if(panel != null) params.put("panel", panel);
            if(hasTouchScreen != null) params.put("hasTouchScreen", hasTouchScreen);
            return screenService.filter(params);
        }
    }

    @ApiOperation("Get screen by screenId")
    @GetMapping("{id}")
    public ResponseEntity<?> getScreenById(@PathVariable("id") long screenId) {
        return screenService.findScreenById(screenId);
    }

    @ApiOperation("Create new screen information")
    @PostMapping("")
    public ResponseEntity<?> createNewScreen(@RequestBody Screen screen) {
        return screenService.insert(screen);
    }

    @ApiOperation("Update screen information")
    @PutMapping("{id}")
    public ResponseEntity<?> updateScreen(@PathVariable("id") long screenId,
                                          @RequestBody Screen screen) {
        return screenService.update(screen, screenId);
    }

    @ApiOperation("Remove screen information")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable("id") long screenId) {
        return screenService.delete(screenId);
    }
}
