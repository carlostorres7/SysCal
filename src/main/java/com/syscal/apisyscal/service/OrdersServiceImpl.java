package com.syscal.apisyscal.service;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.*;
import com.syscal.apisyscal.model.request.OrdersRequestDTO;
import com.syscal.apisyscal.model.response.OrdersResponseDTO;
import com.syscal.apisyscal.model.response.UserResponseDTO;
import com.syscal.apisyscal.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<OrdersResponseDTO> getAll() {
        List<OrdersResponseDTO> ordersDTO = new ArrayList<>();
        List<OrdersEntity> orders = ordersRepository.findAll();
        orders.stream().forEach( order ->  ordersDTO.add(new OrdersResponseDTO(order)));
        return ordersDTO;
    }

    @Override
    public OrdersEntity getOne(Integer orderId) {
        Optional<OrdersEntity> order = ordersRepository.findById(orderId);
        if (!order.isPresent()) {
            throw new BusinessException("Order not Exist","404",HttpStatus.NOT_FOUND);
        }
        return order.get();
    }

    @Override
    public OrdersResponseDTO getOrderById(Integer orderId) {
        OrdersEntity order = getOne(orderId);
        return new OrdersResponseDTO(order);
    }

    @Override
    public List<OrdersResponseDTO> getByTechnician(Integer TechnicianId){
        List<OrdersResponseDTO> ordersDTO = new ArrayList<>();
        List<OrdersEntity> orders = ordersRepository.findAllByTechnical_id(TechnicianId);
        orders.stream().forEach( order ->  ordersDTO.add(new OrdersResponseDTO(order)));
        return ordersDTO;
    };

    @Override
    public OrdersResponseDTO save(OrdersRequestDTO body) {
        UserEntity technical = userService.getOne(body.getTechnical_id());
        ClientEntity client = clientService.getOne(body.getClient_id());
        VehicleEntity vehicle = vehicleService.getOne((body.getVehicle_id()));
        CategoryEntity category = categoryService.getOne(body.getStatus());
        try {
            OrdersEntity newOrder = new OrdersEntity();
            newOrder.setClient(client);
            newOrder.setTechnical(technical);
            newOrder.setVehicle(vehicle);
            newOrder.setStatus(category);
            newOrder.setCreatedbyid(body.getCreatedbyid());
            newOrder.setDescription(body.getDescription());
            OrdersEntity order = ordersRepository.save(newOrder);
            return new OrdersResponseDTO(order);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public OrdersResponseDTO update(Integer orderId, OrdersRequestDTO body) {
        OrdersEntity order = getOne(orderId);
        UserEntity technical = userService.getOne(body.getTechnical_id());
        ClientEntity client = clientService.getOne(body.getClient_id());
        VehicleEntity vehicle = vehicleService.getOne((body.getVehicle_id()));
        CategoryEntity category = categoryService.getOne(body.getStatus());
        try {
            order.setClient(client);
            order.setTechnical(technical);
            order.setVehicle(vehicle);
            order.setStatus(category);
            order.setCreatedbyid(body.getCreatedbyid());
            order.setDescription(body.getDescription());
            OrdersEntity orderUpdate = ordersRepository.save(order);
            return new OrdersResponseDTO(orderUpdate);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Integer id) {
        OrdersEntity order = getOne(id);
        try {
            ordersRepository.delete(order);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
