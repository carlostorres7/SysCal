package com.syscal.apisyscal.repository;

import com.syscal.apisyscal.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    @Query(value = "select * from clients where pname like :pname or plastname like :plastname or cedula like :cedula", nativeQuery = true)
    List<ClientEntity> findAllByPnameOrPlastnameOrCedula(String pname,String plastname,String cedula);
}
