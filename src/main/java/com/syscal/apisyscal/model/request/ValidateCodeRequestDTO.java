package com.syscal.apisyscal.model.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class ValidateCodeRequestDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String username;

    @NotNull
    private Integer code;

}
