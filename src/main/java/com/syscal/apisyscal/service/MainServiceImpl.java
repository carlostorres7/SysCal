package com.syscal.apisyscal.service;

import com.syscal.apisyscal.controller.MainController;
import com.syscal.apisyscal.model.entity.MainEntity;
import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.repository.MainRepository;
import com.syscal.apisyscal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Optional;

@Service
public class MainServiceImpl implements MainService {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private MainRepository mainRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<MainEntity> getAllMains() {
        logger.info("MainService.getAllMains: Consuming repository userRepo ");
        return mainRepository.findAll();
    }

    @Override
    public List<MainEntity> getMainsByUserId(Integer userId) {
        logger.info("MainService.getAllMains: Consuming repository userRepo ");
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getRol().getMains();
        }
        throw new RestClientException("Id user not exist");
    }


}
