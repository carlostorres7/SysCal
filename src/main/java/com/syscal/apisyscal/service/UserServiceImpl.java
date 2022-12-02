package com.syscal.apisyscal.service;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.RolEntity;
import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.model.request.UserRequestDTO;
import com.syscal.apisyscal.model.response.UserResponseDTO;
import com.syscal.apisyscal.repository.RolRepository;
import com.syscal.apisyscal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        List<UserResponseDTO> userDto = new ArrayList<>();
        users.stream().forEach( user -> {
            userDto.add(new UserResponseDTO(user.getId(),user.getUsername(),user.getName(),user.getEmail(),user.getRol().getId(),user.getRol().getName()));
        });
        return userDto;
    }

    @Override
    public UserEntity getOne(Integer userId) {
        Optional<UserEntity> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "The User not Exist");
        }
        return user.get();
    }

    @Override
    public UserResponseDTO getUserById(Integer userId) throws HttpClientErrorException {
        Optional<UserEntity> user = userRepo.findById(userId);
        if (user.isPresent()) {
            UserResponseDTO userDTO = new UserResponseDTO(user.get().getId(),user.get().getUsername(),user.get().getName(),user.get().getEmail(),user.get().getRol().getId(),user.get().getRol().getName());
            return userDTO;
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "The User not Exist");
    }

    @Override
    public UserEntity getUserByUsernameOrEmail(String username) {
        Optional<UserEntity> user = userRepo.findByUsernameOrEmail(username,username);
        if (!user.isPresent()) {
            throw new BusinessException("User not Exist","404", HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    @Override
    public void deleteUserById(Integer userId) {
        Optional<UserEntity> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            throw new BusinessException("User not Exist","404",HttpStatus.NOT_FOUND);
        }
        try {
            userRepo.deleteById(userId);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserEntity updateUserById(Integer userId, UserRequestDTO userDto) {
        Optional<UserEntity> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            throw new BusinessException("User not Exist","404",HttpStatus.NOT_FOUND);
        }
        Optional<RolEntity> role = rolRepository.findById(userDto.getRolId());
        if (!role.isPresent()) {
            throw new BusinessException("Role Id not Exist","404",HttpStatus.NOT_FOUND);
        }
        try {
            user.get().setId(userId);
            user.get().setName(userDto.getName());
            user.get().setUsername(userDto.getUsername());
            user.get().setPassword(encoder.encode(userDto.getPassword()));
            user.get().setEmail(userDto.getEmail());
            user.get().setRol(role.get());
            return userRepo.save(user.get());
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserEntity saveUser(UserRequestDTO user) {
        try {
            UserEntity newUser = new UserEntity();
            Optional<RolEntity> role = rolRepository.findById(user.getRolId());
            newUser.setId(30);
            newUser.setName(user.getName());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(encoder.encode(user.getPassword()));
            newUser.setEmail(user.getEmail());
            if (role.isPresent()) {
                newUser.setRol(role.get());
            }
            return userRepo.save(newUser);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<UserResponseDTO> getAllUsersByRolId(Integer RolId) {
        // 1 step - Create list of type UserResponseDTO
        List<UserResponseDTO> usersByRol = new ArrayList<>();
        // 2 step - Create list of type UserEntity and save
        List<UserEntity> users = userRepo.findByRolId(RolId);
        users.stream().forEach(
                user -> {
                    UserResponseDTO newUser = new UserResponseDTO(user.getId(),user.getUsername(),user.getName(),user.getEmail(),user.getRol().getId(),user.getRol().getName());
                    usersByRol.add(newUser);
                });
        return usersByRol;

    }

}
