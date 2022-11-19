package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.model.interfaces.ControllerCrud;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/services")
@RestController
@Slf4j
public class ServicesController implements ControllerCrud {

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok("hola");
    }

    @Override
    public ResponseEntity<?> getOne(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<?> saveUser(Object body) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Integer id, Object body) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        return null;
    }

}
