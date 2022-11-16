package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/mains")
public class MainController {

    @Autowired
    private MainService mainService;
    Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/{userId}")
    public Object getMainsByUserId(@PathVariable Integer userId){
        logger.info("MainController.getMainsByUserId: consuming service Main getMainsByUserId");
        return mainService.getMainsByUserId(userId);
    }

}
