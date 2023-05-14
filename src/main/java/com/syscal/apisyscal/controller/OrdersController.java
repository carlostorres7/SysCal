package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.OrdersEntity;
import com.syscal.apisyscal.model.entity.ProductEntity;
import com.syscal.apisyscal.model.entity.VehicleEntity;
import com.syscal.apisyscal.model.request.OrdersRequestDTO;
import com.syscal.apisyscal.model.response.OrdersResponseDTO;
import com.syscal.apisyscal.model.response.ResponseDTO;
import com.syscal.apisyscal.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        log.info("OrdersController.getAll");
        List<OrdersResponseDTO> orders = ordersService.getAll();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOne(@PathVariable Integer orderId) {
        log.info("OrdersController.getOne");
        OrdersResponseDTO orders = ordersService.getOrderById(orderId);
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/bytechnician/{technicianId}")
    public ResponseEntity<?> getByTechnician(@PathVariable Integer technicianId) {
        log.info("OrdersController.getByTechnician");
        List<OrdersResponseDTO> orders = ordersService.getByTechnician(technicianId);
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody @NotNull OrdersRequestDTO body) {
        log.info("OrdersController.save");
        OrdersResponseDTO order = ordersService.save(body);
        return ResponseEntity.ok().body(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> update(@PathVariable Integer orderId,@Valid @RequestBody OrdersRequestDTO body) {
        log.info("OrdersController.update");
        if (orderId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", orderId), "400", HttpStatus.BAD_REQUEST);
        }
        OrdersResponseDTO order = ordersService.update(orderId, body);
        return ResponseEntity.ok().body(order);
    }

    @DeleteMapping("/{orderId}")
    private ResponseEntity<?> deleteProduct(@PathVariable Integer orderId) {
        log.info("OrdersController.deleteUser");
        if (orderId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", orderId), "400", HttpStatus.BAD_REQUEST);
        }
        ordersService.delete(orderId);
        ResponseDTO response = new ResponseDTO();
        response.setMessage("Successfully deleted Order");
        response.setStatus(HttpStatus.OK);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "The Order "+orderId +" was removed");
        response.setData(data);
        return ResponseEntity.ok().body(response);
    }

}
