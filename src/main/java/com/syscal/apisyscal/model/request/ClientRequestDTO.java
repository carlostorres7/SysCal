package com.syscal.apisyscal.model.request;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class ClientRequestDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String pname;

    private String sname;

    @NotNull
    @NotBlank
    @NotEmpty
    private String plastname;

    private String slastname;

    @NotNull
    @NotBlank
    @NotEmpty
    private String cedula;

    @NotNull
    @NotBlank
    @NotEmpty
    private String phone;

    @NotNull
    @NotBlank
    @NotEmpty
    private String email;

    @NotNull
    @NotBlank
    @NotEmpty
    private String address;

}
