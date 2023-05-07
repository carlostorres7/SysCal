package com.syscal.apisyscal.model.response;

import com.syscal.apisyscal.model.request.ServiceRequestDTO;
import lombok.Data;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Data
public class ServiceResponseDTO extends ServiceRequestDTO {

    private Integer id;

    private Date createdAt;

    private Date updatedAt;


}
