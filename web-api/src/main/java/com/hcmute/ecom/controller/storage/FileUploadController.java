package com.hcmute.ecom.controller.storage;

import com.hcmute.ecom.service.IStorageService;
import com.hcmute.ecom.service.model.ResponseObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @since 2022-12-01
 * */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class FileUploadController {
    @Autowired
    private IStorageService storageService;

    @ApiOperation("Upload and save image to system")
    @PostMapping("/uploads")
    public ResponseEntity<?> uploadImageFiles(@RequestParam("file") MultipartFile file) {
        try {
            String generateFileName = storageService.storeFile(file);
            return ResponseEntity.ok(new ResponseObject(
                    HttpStatus.CREATED,
                    "Import image successfully!",
                    generateFileName));
        }
        catch (Exception err) {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject(
                            HttpStatus.NOT_IMPLEMENTED,
                            "Fail to upload file"
                    ));
        }
    }

    @ApiOperation("Show image with file name (and file type)")
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String filename) {
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(storageService.readFileContent(filename));
        }
        catch (Exception err) {
            return ResponseEntity.noContent().build();
        }
    }

    @ApiOperation("Show path of file in system")
    @GetMapping("/files/{filename:.+}/path")
    public ResponseEntity<?> getPathOfFile(@PathVariable String filename) {
        try {
            return ResponseEntity
                    .ok()
                    .body(new ResponseObject(
                            HttpStatus.OK,
                            "Get path successfully!",
                            storageService.getPath(filename)
                    ));
        }
        catch (Exception err) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            HttpStatus.NOT_FOUND,
                            "Cannot find this file in system"
                    ));
        }
    }
}
