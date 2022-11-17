package com.syscal.apisyscal.service;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.RolEntity;
import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.model.request.UserRequestDTO;
import com.syscal.apisyscal.model.response.UserControllerResponseDTO;
import com.syscal.apisyscal.repository.RolRepository;
import com.syscal.apisyscal.repository.UserRepository;
import com.syscal.apisyscal.security.jwt.JwtUtils;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public List<UserControllerResponseDTO> getAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        List<UserControllerResponseDTO> userDto = new ArrayList<>();
        users.stream().forEach( user -> {
            userDto.add(new UserControllerResponseDTO(user.getId(),user.getUsername(),user.getName(),user.getEmail()));
        });
        return userDto;
    }

    @Override
    public UserControllerResponseDTO getUserById(Integer userId) throws HttpClientErrorException {
        Optional<UserEntity> user = userRepo.findById(userId);
        if (user.isPresent()) {
            UserControllerResponseDTO userDTO = new UserControllerResponseDTO(user.get().getId(),user.get().getUsername(),user.get().getName(),user.get().getEmail());
            return userDTO;
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "The User not Exist");
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
}
