package com.syscal.apisyscal.repository;

import com.syscal.apisyscal.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> { }
