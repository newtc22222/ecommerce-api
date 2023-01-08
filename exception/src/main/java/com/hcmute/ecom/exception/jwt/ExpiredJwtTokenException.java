package com.hcmute.ecom.exception.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nhat Phi
 * @since 2023-01-06
 * */

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpiredJwtTokenException extends RuntimeException{
    public ExpiredJwtTokenException(String message) {
        super(message);
    }
}
