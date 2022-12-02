package com.hcmute.ecom.controller.laptop;

import com.hcmute.ecom.dto.request.GraphicCardDTO;
import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.service.GraphicCardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ApiOperation("Get all graphic cards information")
    @GetMapping("")
    public ResponseEntity<?> getAllGraphicCards(@RequestParam(value = "type", required = false) String type,
                                                @RequestParam(value = "brand", required = false) String brand) {
        if(type != null && brand != null) {
            return graphicCardService.getGraphicCardsByTypeAndBrand(GraphicCardType.valueOf(type.toUpperCase()), brand);
        }
        if(type != null) {
            try {
                return graphicCardService.getGraphicCardsByType(GraphicCardType.valueOf(type.toUpperCase()));
            }
            catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Please check your \"graphic card type\" request!");
            }
        }
        if(brand != null) {
            return graphicCardService.getGraphicCardsByBrand(brand);
        }
        return graphicCardService.getAllGraphicCards();
    }

    @ApiOperation("Get graphic card by graphicCardId")
    @GetMapping("{id}")
    public ResponseEntity<?> getGraphicCardById(@PathVariable("id") long graphicCardId) {
        return graphicCardService.findGraphicCardById(graphicCardId);
    }

    @ApiOperation("Create new graphic card information")
    @PostMapping("")
    public ResponseEntity<?> createNewGraphicCard(@RequestBody Map<String, String> graphicCardRequest){
        return graphicCardService.insert(GraphicCardDTO.transform(graphicCardRequest));
    }

    @ApiOperation("Update old graphic card's information")
    @PutMapping("{id}")
    public ResponseEntity<?> updateGraphicCardInformation(@PathVariable("id") long graphicCardId,
                                                          @RequestBody Map<String, String> graphicCardRequest) {
        return graphicCardService.update(GraphicCardDTO.transform(graphicCardRequest), graphicCardId);
    }

    @ApiOperation("Remove graphic card from system")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGraphicCardInformation(@PathVariable("id") long graphicCardId) {
        return graphicCardService.delete(graphicCardId);
    }
}
