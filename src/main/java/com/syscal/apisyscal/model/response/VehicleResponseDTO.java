package com.syscal.apisyscal.model.response;

import com.syscal.apisyscal.model.request.VehicleRequestDTO;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.util.Date;

@Data
public class VehicleResponseDTO extends VehicleRequestDTO {

    private Integer id;

    private Date createdAt;

    private Date updatedAt;
}
