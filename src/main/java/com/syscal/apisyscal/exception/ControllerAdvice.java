package com.syscal.apisyscal.exception;

import com.syscal.apisyscal.model.response.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorDTO error = new ErrorDTO();
        error.setCode("400");
        error.setMessage("Input Validation Error");
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setErrors(errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorDTO> businessExceptionHandler(BusinessException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("error",ex.getLocalizedMessage());
        ErrorDTO error = new ErrorDTO();
        error.setCode(ex.getCode());
        error.setMessage("Business Exception Error");
        error.setStatus(ex.getStatus());
        error.setErrors(errors);
        return new ResponseEntity<>(error, ex.getStatus());
    }
/*
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setCode("404");
        error.setMessage(ex.getLocalizedMessage());
        error.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
*/

}

