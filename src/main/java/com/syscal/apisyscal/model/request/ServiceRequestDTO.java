package com.syscal.apisyscal.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ServiceRequestDTO {

    @NotNull
    private String name;

    private Double price;

    private String description;

    @NotNull
    private Integer createdbyid;

}
