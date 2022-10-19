package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.MainEntity;
import com.syscal.apisyscal.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MainService {

    @Autowired
    private MainRepository mainRepository;

    public List<MainEntity> getAllMains() {
        return mainRepository.findAll();
    }


}
