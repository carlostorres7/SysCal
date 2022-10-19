package com.syscal.apisyscal.infraestructure.repositories;

import com.syscal.apisyscal.infraestructure.entities.MainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<MainEntity,Integer > {
}
