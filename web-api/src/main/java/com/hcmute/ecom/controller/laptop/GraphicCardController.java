package com.hcmute.ecom.controller.laptop;

import com.hcmute.ecom.dto.request.GraphicCardDTO;
import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.service.GraphicCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/graphic-cards")
public class GraphicCardController {
    @Autowired
    private GraphicCardService graphicCardService;

    @GetMapping("")
    public ResponseEntity<?> getAllGraphicCards(@RequestParam(value = "type", required = false) String type,
                                                @RequestParam(value = "brand", required = false) String brand) {
        if(type != null && brand != null) {
            return graphicCardService.getGraphicCardsByTypeAndBrand(GraphicCardType.valueOf(type.toUpperCase()), brand);
        }
        if(type != null) {
            return graphicCardService.getGraphicCardsByType(GraphicCardType.valueOf(type.toUpperCase()));
        }
        if(brand != null) {
            return graphicCardService.getGraphicCardsByBrand(brand);
        }
        return graphicCardService.getAllGraphicCards();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getGraphicCardById(@PathVariable("id") long graphicCardId) {
        return graphicCardService.findGraphicCardById(graphicCardId);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewGraphicCard(@RequestBody Map<String, String> graphicCardRequest){
        return graphicCardService.insert(GraphicCardDTO.transform(graphicCardRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateGraphicCardInformation(@PathVariable("id") long graphicCardId,
                                                          @RequestBody Map<String, String> graphicCardRequest) {
        return graphicCardService.update(GraphicCardDTO.transform(graphicCardRequest), graphicCardId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGraphicCardInformation(@PathVariable("id") long graphicCardId) {
        return graphicCardService.delete(graphicCardId);
    }
}
