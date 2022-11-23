package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.MainEntity;

import java.util.List;

public interface MainService {
    Object getAllMains();

    List<MainEntity> getMainsByUserId(Integer userId);
}
