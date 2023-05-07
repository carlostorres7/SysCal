package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.ProductEntity;
import com.syscal.apisyscal.model.entity.ServiceEntity;
import com.syscal.apisyscal.model.interfaces.ControllerCrud;
import com.syscal.apisyscal.model.request.ProductRequestDTO;
import com.syscal.apisyscal.model.request.ServiceRequestDTO;
import com.syscal.apisyscal.model.response.ProductResponseDTO;
import com.syscal.apisyscal.model.response.ResponseDTO;
import com.syscal.apisyscal.model.response.ServiceResponseDTO;
import com.syscal.apisyscal.service.ProductService;
import com.syscal.apisyscal.service.ServicesService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/services")
@RestController
@Slf4j
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("")
    public ResponseEntity<?> getAllServices() {
        log.info("ServicesController.getAllServices");
        List<ServiceResponseDTO> products = servicesService.getAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<?> getService(@PathVariable Integer serviceId) {
        log.info("ServicesController.getService");
        ServiceEntity service = servicesService.getOne(serviceId);
        return ResponseEntity.ok().body(service);
    }

    @PostMapping("")
    public ResponseEntity<?> saveService(@Valid @RequestBody ServiceRequestDTO data) {
        log.info("ServicesController.saveProduct");
        ServiceEntity product = servicesService.save(data);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<?> updateService(@PathVariable Integer serviceId, @Valid @RequestBody ServiceRequestDTO body) {
        log.info("ServicesController.updateService");
        if (serviceId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", serviceId), "400", HttpStatus.BAD_REQUEST);
        }
        ServiceEntity serviceEntity = servicesService.update(serviceId, body);
        return ResponseEntity.ok().body(serviceEntity);
    }

    @DeleteMapping("/{serviceId}")
    private ResponseEntity<?> deleteService(@PathVariable Integer serviceId) {
        log.info("ServicesController.deleteUser");
        if (serviceId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", serviceId), "400", HttpStatus.BAD_REQUEST);
        }
        servicesService.delete(serviceId);
        ResponseDTO response = new ResponseDTO();
        response.setMessage("Successfully deleted Service");
        response.setStatus(HttpStatus.OK);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "The Service "+serviceId +" was removed");
        response.setData(data);
        return ResponseEntity.ok().body(response);
    }


}
