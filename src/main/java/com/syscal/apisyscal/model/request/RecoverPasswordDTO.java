package com.syscal.apisyscal.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RecoverPasswordDTO {

    @NotNull
    @NotBlank
    private String username;

    private String password;

}
