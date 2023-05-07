package com.syscal.apisyscal.security.services;

import jakarta.transaction.Transactional;

import com.syscal.apisyscal.common.utils;
import com.syscal.apisyscal.email.EmailService;
import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.EmailCodeEntity;
import com.syscal.apisyscal.model.request.EmailRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class AuthUserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    utils utils;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserEntity user = userRepository.findByUsername(username)
          .orElseThrow(() ->  new BusinessException("User Not Found with username: " + username, "401", HttpStatus.UNAUTHORIZED));
      return AuthUserDetailsImpl.build(user);
    }

    public void updatePasswordBYUser(String username, String password ) throws  Exception {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BusinessException("User not exist","401", HttpStatus.UNAUTHORIZED);
        }
        UserEntity userEdit = user.get();
        userEdit.setPassword(password);
        userRepository.save(userEdit);
    }

    public void recoverPassword(String username) {
        Optional<UserEntity> user = userRepository.findByUsernameOrEmail(username, username);
        if (!user.isPresent()) {
            throw new BusinessException("User not exist","404", HttpStatus.NOT_FOUND);
        }
        try {
            EmailRequestDTO email = new EmailRequestDTO();
            email.setSubject("Gneración de codigo para Recuperacion de Contraseña Syscal");
            email.setEmail(user.get().getEmail());
            Integer code = utils.generateCode();
            EmailCodeEntity EmailCode = new EmailCodeEntity();
            EmailCode.setCode(code);
            EmailCode.setUserId(user.get().getId());
            //emailCodeService.save(EmailCode);
            email.setContent("<p>Ingresa el siguiente codigo para la recuperacion de tu contraseña </p>" + code  + "<br><p>Syscal 2022  </p>");
            emailService.sendEmail(email);
        } catch (Exception ex) {
            System.out.println(ex);
            throw new BusinessException("Error sending mail","500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
