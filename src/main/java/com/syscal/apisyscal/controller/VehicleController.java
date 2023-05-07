package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.VehicleEntity;
import com.syscal.apisyscal.model.request.VehicleRequestDTO;
import com.syscal.apisyscal.model.response.VehicleResponseDTO;
import com.syscal.apisyscal.model.response.ResponseDTO;
import com.syscal.apisyscal.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicle")
@Slf4j
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("")
    public ResponseEntity<?> getAllVehicles() {
        log.info("VehicleController.getAllVehicles");
        List<VehicleResponseDTO> Vehicles = vehicleService.getAll();
        return ResponseEntity.ok().body(Vehicles);
    }

    @GetMapping("/{VehicleId}")
    public ResponseEntity<?> getVehicle(@PathVariable Integer VehicleId) {
        log.info("VehicleController.getVehicle");
        VehicleEntity Vehicle = vehicleService.getOne(VehicleId);
        return ResponseEntity.ok().body(Vehicle);
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<?> getVehicleByPlaca(@PathVariable String placa) {
        log.info("VehicleController.getVehicleByPlaca");
        VehicleEntity Vehicle = vehicleService.getOneByPlaca(placa);
        return ResponseEntity.ok().body(Vehicle);
    }

    @PostMapping("")
    public ResponseEntity<?> saveVehicle(@Valid @RequestBody VehicleRequestDTO data) {
        log.info("VehicleController.saveVehicle");
        VehicleEntity Vehicle = vehicleService.save(data);
        return ResponseEntity.ok().body(Vehicle);
    }

    @PutMapping("/{VehicleId}")
    public ResponseEntity<?> updateVehicle(@PathVariable Integer VehicleId, @Valid @RequestBody VehicleRequestDTO body) {
        log.info("VehicleController.deleteVehicle");
        if (VehicleId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", VehicleId), "400", HttpStatus.BAD_REQUEST);
        }
        VehicleEntity VehicleEntity = vehicleService.update(VehicleId, body);
        return ResponseEntity.ok().body(VehicleEntity);
    }

    @DeleteMapping("/{VehicleId}")
    private ResponseEntity<?> deleteVehicle(@PathVariable Integer VehicleId) {
        log.info("VehicleController.deleteUser");
        if (VehicleId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", VehicleId), "400", HttpStatus.BAD_REQUEST);
        }
        vehicleService.delete(VehicleId);
        ResponseDTO response = new ResponseDTO();
        response.setMessage("Successfully deleted Vehicle");
        response.setStatus(HttpStatus.OK);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "The Vehicle "+VehicleId +" was removed");
        response.setData(data);
        return ResponseEntity.ok().body(response);
    }



}
