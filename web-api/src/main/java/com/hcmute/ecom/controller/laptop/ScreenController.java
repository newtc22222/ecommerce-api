package com.hcmute.ecom.controller.laptop;

import com.hcmute.ecom.model.laptop.Screen;
import com.hcmute.ecom.service.ScreenService;
import io.swagger.annotations.Api;
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
@Api(tags = "Screen (or Monitor)", value = "Screen controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/screens")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @ApiOperation(value = "Get all screens in system", response = Screen.class)
    @GetMapping("")
    public ResponseEntity<?> getAllScreens(@RequestParam(value = "size", required = false) String size,
                                           @RequestParam(value = "resolution", required = false) String resolution,
                                           @RequestParam(value = "type", required = false) String type,
                                           @RequestParam(value = "panel", required = false) String panel,
                                           @RequestParam(value = "hasTouchScreen", required = false) String hasTouchScreen) {
        if (size == null && resolution == null && type == null && panel == null && hasTouchScreen == null) {
            return screenService.getAllScreens();
        }

        Map<String, String> params = new HashMap<>();
        if(size != null) params.put("size", size);
        if(resolution != null) params.put("resolution", resolution);
        if(type != null) params.put("type", type);
        if(panel != null) params.put("panel", panel);
        if(hasTouchScreen != null) params.put("hasTouchScreen", hasTouchScreen);
        return screenService.filter(params);
    }

    @ApiOperation(value = "Get a screen with id", response = Screen.class)
    @GetMapping("{id}")
    public ResponseEntity<?> getScreenById(@PathVariable("id") long screenId) {
        return screenService.findScreenById(screenId);
    }

    @ApiOperation(value = "Create a new screen", response = ResponseEntity.class)
    @PostMapping("")
    public ResponseEntity<?> createNewScreen(@RequestBody Screen screen) {
        return screenService.insert(screen);
    }

    @ApiOperation(value = "Update a screen", response = ResponseEntity.class)
    @PutMapping("{id}")
    public ResponseEntity<?> updateScreen(@PathVariable("id") long screenId,
                                          @RequestBody Screen screen) {
        return screenService.update(screen, screenId);
    }

    @ApiOperation(value = "Remove a screen from system", response = ResponseEntity.class)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable("id") long screenId) {
        return screenService.delete(screenId);
    }
}
