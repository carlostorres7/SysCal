package com.syscal.apisyscal.repository;

import com.syscal.apisyscal.model.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends JpaRepository<PermissionEntity, Integer> {
}
