package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.model.request.UserRequestDTO;
import com.syscal.apisyscal.model.response.UserControllerResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


public interface UserService {

    public List<UserControllerResponseDTO> getAllUsers();

    public UserControllerResponseDTO getUserById(Integer userId);

    public void deleteUserById(Integer userId);

    public UserEntity updateUserById(Integer userId, UserRequestDTO user);

    public UserEntity saveUser(UserRequestDTO user);

}
