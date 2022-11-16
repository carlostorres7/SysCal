package com.syscal.apisyscal.security.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
  
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserEntity user = userRepository.findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
      return UserDetailsImpl.build(user);
    }

    public void updatePasswordBYUser(String username, String password ) throws  Exception {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new RestClientException("User not exist");
        }
        UserEntity userEdit = user.get();
        userEdit.setPassword(password);
        userRepository.save(userEdit);
    }


}
