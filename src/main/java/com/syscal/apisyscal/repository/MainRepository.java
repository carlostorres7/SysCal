package com.syscal.apisyscal.repository;

import com.syscal.apisyscal.model.entity.MainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<MainEntity,Integer > {
}
