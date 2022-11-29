package com.syscal.apisyscal.model.response;

import com.syscal.apisyscal.model.entity.VehicleEntity;
import com.syscal.apisyscal.model.request.VehicleRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDTO extends VehicleRequestDTO {

    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    public VehicleResponseDTO(VehicleEntity vehicleEntity) {
        super(vehicleEntity);
        this.id = vehicleEntity.getId();
        this.createdAt = vehicleEntity.getCreatedat();
        this.updatedAt = vehicleEntity.getUpdatedat();
    }


}
