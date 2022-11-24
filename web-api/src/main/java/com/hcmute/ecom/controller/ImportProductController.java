package com.hcmute.ecom.controller;

import com.hcmute.ecom.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class ImportProductController {
    @Autowired
    private ImportProductService importProductService;

}
