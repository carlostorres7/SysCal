package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.RolEntity;
import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.model.request.UserRequestDTO;
import com.syscal.apisyscal.model.response.UserControllerResponseDTO;
import com.syscal.apisyscal.repository.RolRepository;
import com.syscal.apisyscal.repository.UserRepository;
import com.syscal.apisyscal.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
    public Boolean deleteUserById(Integer userId) {
        return null;
    }

    @Override
    public Boolean updateUserById(Integer userId, UserEntity user) {
        return null;
    }

    @Override
    public UserEntity saveUser(UserRequestDTO user) {
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
    }
}
