package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.model.request.UserRequestDTO;
import com.syscal.apisyscal.model.response.UserControllerResponseDTO;
import com.syscal.apisyscal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<UserControllerResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        UserControllerResponseDTO users = userService.getUserById(id);
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
        return ResponseEntity.ok().body("Successfully deleted User");
    }

}
