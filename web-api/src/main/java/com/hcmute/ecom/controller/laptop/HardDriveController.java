package com.hcmute.ecom.controller.laptop;

import com.hcmute.ecom.dto.request.HardDriveDTO;
import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.service.HardDriveService;
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
@RequestMapping("/api/v1/hard-drives")
public class HardDriveController {
    @Autowired
    private HardDriveService hardDriveService;

    @GetMapping("")
    public ResponseEntity<?> getAllHardDrives(@RequestParam(value = "type", required = false) String type) {

        if(type != null) {
            return hardDriveService.getHardDrivesByType(HardDriveType.valueOf(type.toUpperCase()));
        }
        return hardDriveService.getAllHardDrive();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getHardDriveById(@PathVariable("id") long hardDriveId) {
        return hardDriveService.findHardDriveById(hardDriveId);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewHardDrive(@RequestBody Map<String, String> hardDriveRequest){
        return hardDriveService.insert(HardDriveDTO.transform(hardDriveRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateHardDriveInformation(@PathVariable("id") long hardDriveId,
                                                          @RequestBody Map<String, String> hardDriveRequest) {
        return hardDriveService.update(HardDriveDTO.transform(hardDriveRequest), hardDriveId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteHardDriveInformation(@PathVariable("id") long hardDriveId) {
        return hardDriveService.delete(hardDriveId);
    }
}
