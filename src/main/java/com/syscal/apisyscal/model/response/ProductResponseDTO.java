package com.syscal.apisyscal.model.response;

import com.syscal.apisyscal.model.request.ProductRequestDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ProductResponseDTO extends ProductRequestDTO {

    private Integer id;
    private Date createdAt;
    private Date updatedAt;

}
