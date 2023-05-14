package com.syscal.apisyscal.repository;

import com.syscal.apisyscal.model.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {

    List<OrdersEntity> findAllByTechnical_id(Integer Technical_id);

}
