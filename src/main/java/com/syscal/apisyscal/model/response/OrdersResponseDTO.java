package com.syscal.apisyscal.model.response;

import com.syscal.apisyscal.model.entity.*;
import com.syscal.apisyscal.model.request.OrdersRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponseDTO extends OrdersRequestDTO {

    private Integer id;

    private UserResponseDTO technical;

    private ClientEntity client;

    private VehicleEntity vehicle;

    private CategoryEntity status_order;

    private Date createdat;

    private Date updatedat;

    public OrdersResponseDTO(OrdersEntity ordersEntity) {
        super(ordersEntity);
        this.id = ordersEntity.getId();
        this.technical = new UserResponseDTO(ordersEntity.getTechnical());
        this.client = ordersEntity.getClient();
        this.vehicle = ordersEntity.getVehicle();
        this.status_order = ordersEntity.getStatus();
        this.createdat = ordersEntity.getCreatedat();
        this.updatedat = ordersEntity.getUpdatedat();
    }

}
