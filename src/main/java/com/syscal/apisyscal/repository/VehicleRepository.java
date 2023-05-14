package com.syscal.apisyscal.repository;

import com.syscal.apisyscal.model.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {
    Optional<VehicleEntity> findByPlacaContaining(String placa);

}
