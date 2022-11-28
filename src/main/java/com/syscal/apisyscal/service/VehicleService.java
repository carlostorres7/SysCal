package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.VehicleEntity;
import com.syscal.apisyscal.model.request.VehicleRequestDTO;
import com.syscal.apisyscal.model.response.VehicleResponseDTO;

import java.util.List;

public interface VehicleService {

    public List<VehicleResponseDTO> getAll();

    public VehicleResponseDTO getOne(Integer userId);

    public void delete(Integer userId);

    public VehicleEntity update(Integer userId, VehicleRequestDTO user);

    public VehicleEntity save(VehicleRequestDTO user);

}
