package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.ServiceEntity;
import com.syscal.apisyscal.model.request.ServiceRequestDTO;
import com.syscal.apisyscal.model.response.ServiceResponseDTO;

import java.util.List;

public interface ServicesService {

    public List<ServiceResponseDTO> getAll();

    public ServiceEntity getOne(Integer Id);

    public ServiceEntity save(ServiceRequestDTO body);

    public ServiceEntity update(Integer Id, ServiceRequestDTO body);

    public void delete(Integer Id);

}
