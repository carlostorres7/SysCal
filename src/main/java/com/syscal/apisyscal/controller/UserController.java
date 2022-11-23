package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.model.request.UserRequestDTO;
import com.syscal.apisyscal.model.response.ResponseDTO;
import com.syscal.apisyscal.model.response.UserResponseDTO;
import com.syscal.apisyscal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        UserResponseDTO users = userService.getUserById(id);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<?> getByRol(@PathVariable Integer id) {
        List<UserResponseDTO> users = userService.getAllUsersByRolId(id);
        return ResponseEntity.ok().body(users);
    }


    @PostMapping("")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequestDTO data) {
        log.info("UserController.saveUser");
        UserEntity user = userService.saveUser(data);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{Userid}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable @NotNull @Min(1) Integer Userid, @Valid @RequestBody UserRequestDTO body) {
        log.info("UserController.updateUser");
        if (Userid < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", Userid), "400", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = userService.updateUserById(Userid, body);
        return ResponseEntity.ok().body(userEntity);
    }

    @DeleteMapping("/{Userid}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer Userid) {
        log.info("UserController.deleteUser");
        if (Userid < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", Userid), "400", HttpStatus.BAD_REQUEST);
        }
        userService.deleteUserById(Userid);
        ResponseDTO response = new ResponseDTO();
        response.setMessage("Successfully deleted User");
        response.setStatus(HttpStatus.OK);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "The User "+Userid +" was removed");
        response.setData(data);
        return ResponseEntity.ok().body(response);
    }

}
