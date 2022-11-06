package com.syscal.apisyscal.service;

import com.syscal.apisyscal.controller.MainController;
import com.syscal.apisyscal.repository.MainRepository;
import com.syscal.apisyscal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private MainRepository mainRepository;

    @Autowired
    private UserRepository userRepository;

    public Object getAllMains() {
        logger.info("MainService.getAllMains: Consuming repository userRepo ");
        mainRepository.findAll();
        return userRepository.findAll();
    }


}
