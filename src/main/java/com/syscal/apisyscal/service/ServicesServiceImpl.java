package com.syscal.apisyscal.service;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.ServiceEntity;
import com.syscal.apisyscal.model.request.ServiceRequestDTO;
import com.syscal.apisyscal.model.response.ServiceResponseDTO;
import com.syscal.apisyscal.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;


    @Override
    public List<ServiceResponseDTO> getAll() {
        List<ServiceResponseDTO> servicesDTO = new ArrayList<>();
        List<ServiceEntity> services = serviceRepository.findAll();
        services.forEach(service -> {
            ServiceResponseDTO serviceDTO = new ServiceResponseDTO();
            serviceDTO.setId(service.getId());
            serviceDTO.setName(service.getName());
            serviceDTO.setPrice(service.getPrice());
            serviceDTO.setDescription(service.getDescription());
            serviceDTO.setCreatedbyid(service.getCreatedById());
            serviceDTO.setCreatedAt(service.getCreatedAt());
            serviceDTO.setUpdatedAt(service.getUpdatedAt());
            servicesDTO.add(serviceDTO);
        });
        return servicesDTO;
    }

    @Override
    public ServiceEntity getOne(Integer Id) {
        Optional<ServiceEntity> service = serviceRepository.findById(Id);
        if (!service.isPresent()) {
            throw new BusinessException("Service not Exist","404", HttpStatus.NOT_FOUND);
        }
        return service.get();
    }

    @Override
    public ServiceEntity save(ServiceRequestDTO body) {
        ServiceEntity newService = new ServiceEntity();
        newService.setName(body.getName());
        newService.setPrice(body.getPrice());
        newService.setCreatedById(body.getCreatedbyid());
        newService.setDescription(body.getDescription());
        try {
            return serviceRepository.save(newService);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ServiceEntity update(Integer Id, ServiceRequestDTO body) {
        System.out.println(Id);
        ServiceEntity service = getOne(Id);
        try {
            service.setName(body.getName());
            service.setPrice(body.getPrice());
            service.setDescription(body.getDescription());
            return serviceRepository.save(service);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Integer Id) {
        ServiceEntity service = getOne(Id);
        try {
            serviceRepository.delete(service);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
