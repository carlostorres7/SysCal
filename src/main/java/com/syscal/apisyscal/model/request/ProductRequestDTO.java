package com.syscal.apisyscal.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class ProductRequestDTO {

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private Integer createdbyid;

}
