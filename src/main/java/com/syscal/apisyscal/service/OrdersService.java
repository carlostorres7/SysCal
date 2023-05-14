package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.OrdersEntity;
import com.syscal.apisyscal.model.request.OrdersRequestDTO;
import com.syscal.apisyscal.model.response.OrdersResponseDTO;

import java.util.List;
















public interface OrdersService {

    public List<OrdersResponseDTO> getAll();

    public OrdersEntity getOne(Integer orderId);

    public List<OrdersResponseDTO> getByTechnician(Integer TechnicianId);

    public OrdersResponseDTO getOrderById(Integer orderId);

    public OrdersResponseDTO save(OrdersRequestDTO body);

    public OrdersResponseDTO update(Integer orderId,OrdersRequestDTO body);

    public void delete(Integer id);

}
