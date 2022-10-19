package com.syscal.apisyscal.services;

import com.syscal.apisyscal.infraestructure.entities.MainEntity;
import com.syscal.apisyscal.infraestructure.repositories.MainRepository;
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
