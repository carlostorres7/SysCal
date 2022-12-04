package com.syscal.apisyscal.model.request;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
