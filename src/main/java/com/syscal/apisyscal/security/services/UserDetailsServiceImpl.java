package com.syscal.apisyscal.security.services;

import javax.transaction.Transactional;

import com.syscal.apisyscal.email.EmailService;
import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.request.EmailRequestDTO;
import com.syscal.apisyscal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.repository.UserRepository;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserEntity user = userRepository.findByUsername(username)
          .orElseThrow(() ->  new BusinessException("User Not Found with username: " + username, "401", HttpStatus.UNAUTHORIZED));
      return UserDetailsImpl.build(user);
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
            email.setSubject("Recover Password");
            email.setEmail(user.get().getEmail());
            email.setContent("Recover password 789456123");
            emailService.sendEmail(email);
        } catch (Exception ex) {
            throw new BusinessException("Error sending mail","500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
