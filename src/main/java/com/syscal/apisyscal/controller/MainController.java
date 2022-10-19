package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mains")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("")
    public Object helloWorld(){
        return mainService.getAllMains();
    }


}
