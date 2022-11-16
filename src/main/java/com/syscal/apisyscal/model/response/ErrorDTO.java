package com.syscal.apisyscal.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorDTO {

    private String code;
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private Map<String, Object> errors;

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }
}
