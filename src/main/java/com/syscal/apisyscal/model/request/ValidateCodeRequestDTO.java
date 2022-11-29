package com.syscal.apisyscal.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ValidateCodeRequestDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String username;

    @NotNull
    private Integer code;

}
