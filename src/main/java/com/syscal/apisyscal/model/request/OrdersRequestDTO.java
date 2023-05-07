package com.syscal.apisyscal.model.request;

import com.syscal.apisyscal.model.entity.OrdersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@NotNull
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRequestDTO {

    @NotNull
    private Integer vehicle_id;

    @NotNull
    private Integer technical_id;

    @NotNull
    private Integer client_id;

    @NotNull
    private Integer createdbyid;

    @NotNull
    private Integer status;

    private String description;

    public OrdersRequestDTO(OrdersEntity ordersEntity) {
        this.vehicle_id = ordersEntity.getVehicle().getId();
        this.technical_id = ordersEntity.getTechnical().getId();
        this.client_id = ordersEntity.getClient().getId();
        this.status = ordersEntity.getStatus().getId();
        this.createdbyid = ordersEntity.getCreatedbyid();
        this.description = ordersEntity.getDescription();
    }

}
