package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.VehicleEntity;
import com.syscal.apisyscal.model.request.VehicleRequestDTO;
import com.syscal.apisyscal.model.response.VehicleResponseDTO;

import java.util.List;

public interface VehicleService {

    public List<VehicleResponseDTO> getAll();

    public VehicleEntity getOne(Integer Id);

    public VehicleEntity getOneByPlaca(String placa);

    public void delete(Integer Id);

    public VehicleEntity update(Integer Id, VehicleRequestDTO body);

    public VehicleEntity save(VehicleRequestDTO body);

}
