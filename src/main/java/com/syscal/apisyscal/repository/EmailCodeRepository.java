package com.syscal.apisyscal.repository;

import com.syscal.apisyscal.model.entity.EmailCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailCodeRepository extends JpaRepository<EmailCodeEntity, Integer> {

    @Query(value = "select * from email_code where user_id = ?", nativeQuery = true)
    List<EmailCodeEntity> findByUserId(Integer UserId);

}
