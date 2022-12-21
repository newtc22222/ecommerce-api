package com.hcmute.ecom.controller.storage;

import com.hcmute.ecom.service.IStorageService;
import com.hcmute.ecom.service.model.ResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @since 2022-12-01
 * */
@Api(tags = "Upload images", value = "File upload controller")
@CrossOrigin(value = { "*" })
@RestController
@RequestMapping("/api/v1")
public class FileUploadController {
    @Autowired
    private IStorageService storageService;

    @ApiOperation(value = "Upload and save an image to server", response = ResponseEntity.class)
    @PostMapping("/uploads")
    public ResponseEntity<?> uploadImageFile(@RequestParam("file") MultipartFile file) {
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

    @ApiOperation(value = "Upload and save multiple images to server", response = ResponseEntity.class)
    @PostMapping("/uploads-multiple")
    public ResponseEntity<?> uploadMultipleImageFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            List<String> generateFileNameList = new ArrayList<>();
            Arrays.stream(files).forEach(file -> {
                String generateFileName = storageService.storeFile(file);
                generateFileNameList.add(generateFileName);
            });
            return ResponseEntity.ok(new ResponseObject(
                    HttpStatus.CREATED,
                    "Import all images successfully!",
                    generateFileNameList));
        }
        catch (Exception err) {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body(new ResponseObject(
                            HttpStatus.NOT_IMPLEMENTED,
                            "Fail to upload files"
                    ));
        }
    }

    @ApiOperation(value = "Show image with file name (and file type)", response = ResponseEntity.class)
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

    @ApiOperation(value = "Show path of file in system", response = ResponseEntity.class)
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
