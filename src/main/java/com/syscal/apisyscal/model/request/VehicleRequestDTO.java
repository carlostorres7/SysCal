package com.syscal.apisyscal.model.request;

import com.syscal.apisyscal.model.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.syscal.apisyscal.model.entity.VehicleEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String brand;

    @NotNull
    @NotEmpty
    @NotBlank
    private String reference;

    @NotNull
    @NotEmpty
    @NotBlank
    private String placa;

    @NotNull
    @NotEmpty
    @NotBlank
    private String color;

    @NotNull
    private Integer createdbyid;

    public VehicleRequestDTO(VehicleEntity vehicleEntity) {
        this.brand = vehicleEntity.getBrand();
        this.reference = vehicleEntity.getReference();
        this.placa = vehicleEntity.getPlaca();
        this.color = vehicleEntity.getColor();
        this.createdbyid = vehicleEntity.getCreatedbyid();
    }

}