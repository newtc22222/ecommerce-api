package com.hcmute.ecom.service.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Nhat Phi
 * @since 2022-11-24
 * */
public interface ResponseCUDObject {
    /**
     * Blueprint for the response of Create, Update, Delete Actions
     * */
    static ResponseEntity<?> of(boolean condition,
                                HttpStatus statusTrue, String messageTrue,
                                HttpStatus statusFalse, String messageFalse) {
        return (condition)
                ? ResponseEntity
                    .status(statusTrue)
                    .body(new ResponseObject(
                            statusTrue,
                            messageTrue
                    ))
                : ResponseEntity
                    .status(statusFalse)
                    .body(new ResponseObject(
                            statusFalse,
                            messageFalse
                    ));
    }

    static ResponseEntity<?> of(boolean condition,
                                HttpStatus statusTrue, String messageTrue, Object data,
                                HttpStatus statusFalse, String messageFalse) {
        return (condition)
                ? ResponseEntity
                    .status(statusTrue)
                    .body(new ResponseObject(
                            statusTrue,
                            messageTrue,
                            data
                    ))
                : ResponseEntity
                    .status(statusFalse)
                    .body(new ResponseObject(
                            statusFalse,
                            messageFalse
                    ));
    }
}
