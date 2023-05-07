package com.syscal.apisyscal.model.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

public interface ControllerCrud<T> {

    @GetMapping("")
    public ResponseEntity<?> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id);

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody T body);

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody T body);

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id);

}
