package com.syscal.apisyscal.repository;

import com.syscal.apisyscal.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    List<UserEntity> findByRolId(Integer RolId);

    Boolean existsByUsername(String username);
  
    Boolean existsByEmail(String email);

}
