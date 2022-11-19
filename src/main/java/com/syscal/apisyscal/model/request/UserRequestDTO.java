package com.syscal.apisyscal.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRequestDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String username;

    private String password;

    @NotNull
    @NotBlank
    private String email;

    @JsonProperty("rol_id")
    @NotNull
    private Integer rolId;

}
