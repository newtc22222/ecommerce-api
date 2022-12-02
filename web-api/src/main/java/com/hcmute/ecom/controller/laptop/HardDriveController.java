package com.hcmute.ecom.controller.laptop;

import com.hcmute.ecom.dto.request.HardDriveDTO;
import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.service.HardDriveService;
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
@RequestMapping("/api/v1/hard-drives")
public class HardDriveController {
    @Autowired
    private HardDriveService hardDriveService;

    @ApiOperation("Get all hard drives in system")
    @GetMapping("")
    public ResponseEntity<?> getAllHardDrives(@RequestParam(value = "type", required = false) String type) {

        if(type != null) {
            try {
                return hardDriveService.getHardDrivesByType(HardDriveType.valueOf(type.toUpperCase()));
            }
            catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please check your \"hard drive type\" request!");
            }
        }
        return hardDriveService.getAllHardDrive();
    }

    @ApiOperation("Get hard drive by hardDriveId")
    @GetMapping("{id}")
    public ResponseEntity<?> getHardDriveById(@PathVariable("id") long hardDriveId) {
        return hardDriveService.findHardDriveById(hardDriveId);
    }

    @ApiOperation("Create new hard drive information")
    @PostMapping("")
    public ResponseEntity<?> createNewHardDrive(@RequestBody Map<String, String> hardDriveRequest){
        return hardDriveService.insert(HardDriveDTO.transform(hardDriveRequest));
    }

    @ApiOperation("Update hard drive information")
    @PutMapping("{id}")
    public ResponseEntity<?> updateHardDriveInformation(@PathVariable("id") long hardDriveId,
                                                          @RequestBody Map<String, String> hardDriveRequest) {
        return hardDriveService.update(HardDriveDTO.transform(hardDriveRequest), hardDriveId);
    }

    @ApiOperation("Delete hard drive information")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteHardDriveInformation(@PathVariable("id") long hardDriveId) {
        return hardDriveService.delete(hardDriveId);
    }
}
