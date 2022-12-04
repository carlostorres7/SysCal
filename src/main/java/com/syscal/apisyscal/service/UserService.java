package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.model.request.UserRequestDTO;
import com.syscal.apisyscal.model.response.UserResponseDTO;

import java.util.List;


public interface UserService {

    public List<UserResponseDTO> getAllUsers();

    public UserEntity getOne(Integer userId);

    public UserResponseDTO getUserById(Integer userId);

    public UserEntity getUserByUsernameOrEmail(String username);

    public void deleteUserById(Integer userId);

    public UserEntity updateUserById(Integer userId, UserRequestDTO user);

    public UserEntity saveUser(UserRequestDTO user);

    public List<UserResponseDTO> getAllUsersByRolId(Integer RolId);

}
