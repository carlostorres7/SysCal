package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mains")
public class MainController {

    @Autowired
    private MainService mainService;
    Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("")
    public Object helloWorld(){
        logger.info("MainController.helloWorld: Consumiendo servicio Main getAll");

        return mainService.getAllMains();
    }


}
