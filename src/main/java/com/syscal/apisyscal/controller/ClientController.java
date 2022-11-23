package com.syscal.apisyscal.controller;


import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.ClientEntity;
import com.syscal.apisyscal.model.request.ClientRequestDTO;
import com.syscal.apisyscal.model.response.ClientResponseDTO;
import com.syscal.apisyscal.model.response.ResponseDTO;
import com.syscal.apisyscal.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        log.info("ClientController.getAll");
        List<ClientResponseDTO> clients = clientService.getAll();
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getOne(@PathVariable Integer clientId) {
        log.info("ClientController.getOne");
        ClientEntity client = clientService.getOne(clientId);
        return ResponseEntity.ok().body(client);
    }


    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody ClientRequestDTO body) {
        log.info("ClientController.save");
        ClientEntity client = clientService.save(body);
        return ResponseEntity.ok().body(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<?> update(@PathVariable Integer clientId, @Valid @RequestBody ClientRequestDTO body) {
        log.info("ClientController.update");
        if (clientId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", clientId), "400", HttpStatus.BAD_REQUEST);
        }
        ClientEntity client = clientService.update(clientId, body);
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> delete(@PathVariable Integer clientId) {
        log.info("ClientController.delete");
        if (clientId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", clientId), "400", HttpStatus.BAD_REQUEST);
        }
        clientService.delete(clientId);
        ResponseDTO response = new ResponseDTO();
        response.setMessage("Successfully deleted User");
        response.setStatus(HttpStatus.OK);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "The Client "+clientId +" was removed");
        response.setData(data);
        return ResponseEntity.ok().body(response);
    }

}
