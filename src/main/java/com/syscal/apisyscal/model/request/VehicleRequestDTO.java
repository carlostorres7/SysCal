package com.syscal.apisyscal.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.syscal.apisyscal.model.entity.VehicleEntity} entity
 */
@Data
public class VehicleRequestDTO {
    private String brand;
    private String placa;
    private String color;
    private Integer createdById;
}