package com.hcmute.ecom.controller.laptop;

import com.hcmute.ecom.dto.request.GraphicCardDTO;
import com.hcmute.ecom.enums.product.GraphicCardType;
import com.hcmute.ecom.model.laptop.GraphicCard;
import com.hcmute.ecom.service.GraphicCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@Api(tags = "Graphic Card (display)", value = "GraphicCard controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/graphic-cards")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
public class GraphicCardController {
    @Autowired
    private GraphicCardService graphicCardService;

    @ApiOperation(value = "Get all graphic cards information", response = GraphicCard.class)
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

    @ApiOperation(value = "Get graphic card by graphicCardId", response = GraphicCard.class)
    @GetMapping("{id}")
    public ResponseEntity<?> getGraphicCardById(@PathVariable("id") long graphicCardId) {
        return graphicCardService.findGraphicCardById(graphicCardId);
    }

    @ApiOperation(value = "Create new graphic card information", response = ResponseEntity.class)
    @PostMapping("")
    public ResponseEntity<?> createNewGraphicCard(@RequestBody GraphicCardDTO graphicCardDTO){
        return graphicCardService.insert(GraphicCardDTO.transform(graphicCardDTO));
    }

    @ApiOperation(value = "Update old graphic card's information", response = ResponseEntity.class)
    @PutMapping("{id}")
    public ResponseEntity<?> updateGraphicCardInformation(@PathVariable("id") long graphicCardId,
                                                          @RequestBody GraphicCardDTO graphicCardDTO) {
        return graphicCardService.update(GraphicCardDTO.transform(graphicCardDTO), graphicCardId);
    }

    @ApiOperation(value = "Remove graphic card from system", response = ResponseEntity.class)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGraphicCardInformation(@PathVariable("id") long graphicCardId) {
        return graphicCardService.delete(graphicCardId);
    }
}
