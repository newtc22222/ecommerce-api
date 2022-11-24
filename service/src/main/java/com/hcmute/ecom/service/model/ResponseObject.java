package com.hcmute.ecom.service.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ResponseObject {
    private HttpStatus status;
    private String message;
    private List<Object> data;

    public ResponseObject(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<>();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
