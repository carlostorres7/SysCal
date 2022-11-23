package com.syscal.apisyscal.model.response;

import com.syscal.apisyscal.model.entity.ClientEntity;
import com.syscal.apisyscal.model.request.ClientRequestDTO;
import lombok.Data;

@Data
public class ClientResponseDTO extends ClientRequestDTO {

    private Integer id;

    public ClientResponseDTO(ClientEntity clientEntity) {
        this.id = clientEntity.getId();
        this.setPname(clientEntity.getPname());
        this.setSname(clientEntity.getSname());
        this.setPlastname(clientEntity.getPlastname());
        this.setSlastname(clientEntity.getSlastname());
        this.setCedula(clientEntity.getCedula());
        this.setPhone(clientEntity.getPhone());
        this.setEmail(clientEntity.getEmail());
        this.setAddress(clientEntity.getAddress());
    }

}
