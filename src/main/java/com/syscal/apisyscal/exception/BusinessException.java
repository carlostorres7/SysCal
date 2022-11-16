package com.syscal.apisyscal.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BusinessException extends RuntimeException {

    private HttpStatus status;
    private String code;

    public BusinessException(String message, String code,HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
