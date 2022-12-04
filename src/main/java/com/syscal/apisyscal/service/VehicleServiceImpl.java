package com.syscal.apisyscal.service;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.ClientEntity;
import com.syscal.apisyscal.model.entity.VehicleEntity;
import com.syscal.apisyscal.model.request.ClientRequestDTO;
import com.syscal.apisyscal.model.request.VehicleRequestDTO;
import com.syscal.apisyscal.model.response.ClientResponseDTO;
import com.syscal.apisyscal.model.response.VehicleResponseDTO;
import com.syscal.apisyscal.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Order(value = 1)
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<VehicleResponseDTO> getAll() {
        // create array empty of VehicleResponseDTO
        List<VehicleResponseDTO> vehiclesDTO = new ArrayList<>();
        // call all register of the table Vehicle and save in to array of VehicleEntity
        List<VehicleEntity> vehicles = vehicleRepository.findAll();
        // Travel array VehicleEntity and save each in array VehicleResponseDTO
        vehicles.stream().forEach( vehicle -> {
            vehiclesDTO.add(new VehicleResponseDTO(vehicle));
        });
        // return array VehicleResponseDTO
        return vehiclesDTO;
    }

    @Override
    public VehicleEntity getOne(Integer Id) {
        // verify exist of the Id in table Vehicle
        Optional<VehicleEntity> vehicle = vehicleRepository.findById(Id);
        // if not exist return error 404
        if (!vehicle.isPresent()) {
            throw new BusinessException("Vehicle not Exist","404", HttpStatus.NOT_FOUND);
        }
        // if exist return Object vehicleEntity
        return vehicle.get();
    }

    @Override
    public VehicleEntity getOneByPlaca(String placa) {
        // verify exist of the Id in table Vehicle
        Optional<VehicleEntity> vehicle = vehicleRepository.findByPlaca(placa);
        // if not exist return error 404
        if (!vehicle.isPresent()) {
            throw new BusinessException("Vehicle not Exist","404", HttpStatus.NOT_FOUND);
        }
        // if exist return Object vehicleEntity
        return vehicle.get();
    }

    @Override
    public void delete(Integer Id) {
        // verify exist of the Id in table Vehicle and return the object existing
        VehicleEntity vehicle = getOne(Id);
        try {
            // deleted register in table by id
            vehicleRepository.delete(vehicle);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public VehicleEntity update(Integer Id, VehicleRequestDTO body) {
        // verify exist of the Id in table Vehicle and return the object existing
        VehicleEntity vehicle = getOne(Id);
        try {
            // Map object Exist VehicleEntity To object VehicleRequestDTO
            VehicleEntity updateVehicle = convertExistToEntity(vehicle, body);
            // save all changes
            return vehicleRepository.save(updateVehicle);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public VehicleEntity save(VehicleRequestDTO body) {
        // Map a new object VehicleEntity To object VehicleRequestDTO
        VehicleEntity vehicle = convertDtoToEntity(body);
        try {
            // save all changes
            return vehicleRepository.save(vehicle);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private VehicleEntity convertDtoToEntity(VehicleRequestDTO vehicleDto) {
        return convertExistToEntity(new VehicleEntity(), vehicleDto);
    }

    private VehicleEntity convertExistToEntity(VehicleEntity vehicle, VehicleRequestDTO vehicleDto) {
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setReference(vehicleDto.getReference());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setPlaca(vehicleDto.getPlaca());
        vehicle.setCreatedbyid(vehicleDto.getCreatedbyid());
        return vehicle;
    }

}
