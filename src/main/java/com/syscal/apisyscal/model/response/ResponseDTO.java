package com.syscal.apisyscal.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private String message;
    private Map<String, Object> Data;
    private LocalDateTime timestamp;
    private HttpStatus status;

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }
}
