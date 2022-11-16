package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.model.request.UserRequestDTO;
import com.syscal.apisyscal.model.response.UserControllerResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


public interface UserService {

    public List<UserControllerResponseDTO> getAllUsers();

    public UserControllerResponseDTO getUserById(Integer userId) throws HttpClientErrorException;

    public Boolean deleteUserById(Integer userId);

    public Boolean updateUserById(Integer userId, UserEntity user);

    public UserEntity saveUser(UserRequestDTO user);

}
