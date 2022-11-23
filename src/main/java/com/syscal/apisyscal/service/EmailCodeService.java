package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.EmailCodeEntity;
import com.syscal.apisyscal.model.interfaces.ServiceCrud;

import java.util.List;

public interface EmailCodeService {


    List<EmailCodeEntity> getAll();

    EmailCodeEntity getOne(Integer id);

    EmailCodeEntity save(EmailCodeEntity body);

    EmailCodeEntity update(Integer id, EmailCodeEntity body);

    void delete(Integer id);
}
