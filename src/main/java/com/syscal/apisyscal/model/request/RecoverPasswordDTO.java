package com.syscal.apisyscal.model.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class RecoverPasswordDTO {

    @NotNull
    @NotBlank
    private String username;

    private String password;

}
