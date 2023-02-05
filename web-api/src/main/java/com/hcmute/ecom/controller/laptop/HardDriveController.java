package com.hcmute.ecom.controller.laptop;

import com.hcmute.ecom.dto.request.HardDriveDTO;
import com.hcmute.ecom.enums.product.HardDriveType;
import com.hcmute.ecom.model.laptop.HardDrive;
import com.hcmute.ecom.service.HardDriveService;
import com.hcmute.ecom.service.model.ResponseObject;
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
@Api(tags = "Hard drive of laptop", value = "HardDrive controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1/hard-drives")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
public class HardDriveController {
    @Autowired
    private HardDriveService hardDriveService;

    @ApiOperation(value = "Get all hard drives in system", response = HardDrive.class)
    @GetMapping("")
    public ResponseEntity<?> getAllHardDrives(@RequestParam(value = "type", required = false) String type) {

        if(type != null) {
            try {
                return hardDriveService.getHardDrivesByType(HardDriveType.valueOf(type.toUpperCase()));
            }
            catch (Exception err) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject(
                                HttpStatus.BAD_REQUEST,
                                "Please check your \"hard drive type\" request!"
                        ));
            }
        }
        return hardDriveService.getAllHardDrive();
    }

    @ApiOperation(value = "Get a hard drives with id", response = HardDrive.class)
    @GetMapping("{id}")
    public ResponseEntity<?> getHardDriveById(@PathVariable("id") long hardDriveId) {
        return hardDriveService.findHardDriveById(hardDriveId);
    }

    @ApiOperation(value = "Create a new hard drive", response = ResponseEntity.class)
    @PostMapping("")
    public ResponseEntity<?> createNewHardDrive(@RequestBody HardDriveDTO hardDriveDTO){
        return hardDriveService.insert(HardDriveDTO.transform(hardDriveDTO));
    }

    @ApiOperation(value = "Update a hard drive", response = ResponseEntity.class)
    @PutMapping("{id}")
    public ResponseEntity<?> updateHardDriveInformation(@PathVariable("id") long hardDriveId,
                                                        @RequestBody HardDriveDTO hardDriveDTO) {
        return hardDriveService.update(HardDriveDTO.transform(hardDriveDTO), hardDriveId);
    }

    @ApiOperation(value = "Remove a hard drive from system", response = ResponseEntity.class)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteHardDriveInformation(@PathVariable("id") long hardDriveId) {
        return hardDriveService.delete(hardDriveId);
    }
}
