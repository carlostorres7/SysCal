package com.syscal.apisyscal.controller;

import javax.validation.Valid;

import com.syscal.apisyscal.email.EmailService;
import com.syscal.apisyscal.model.request.RecoverPasswordDTO;
import com.syscal.apisyscal.security.services.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syscal.apisyscal.model.request.AuthRequestDTO;
import com.syscal.apisyscal.model.response.UserInfoResponse;
import com.syscal.apisyscal.security.jwt.JwtUtils;
import com.syscal.apisyscal.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder encoder;
  
    @Autowired
    JwtUtils jwtUtils;
  
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequestDTO loginRequest) {
        log.info("Auth Controller - Login");
        emailService.sendEmailTool("Inicio de sesion x", "carlostorres8791@gmail.com", "Prueba de correo");
        UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(userAuth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        UserInfoResponse response = new UserInfoResponse(userDetails.getId(), userDetails.getName() ,userDetails.getUsername(), userDetails.getEmail(), jwtCookie.toString(),userDetails.getAuthorities(), userDetails.getRoles() );
        return ResponseEntity.ok().header("auth_token", jwtCookie.toString()).body(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody RecoverPasswordDTO body ) throws Exception {
        log.info("Auth Controller - Change Password");
        String password = encoder.encode(body.getPassword());
        userService.updatePasswordBYUser(body.getUsername(),password);
        return  ResponseEntity.ok("User update with successful");
    }

    @PostMapping("/sign-out")
    public ResponseEntity<?> logoutUser() {
        log.info("Auth Controller - Sign Out");
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("You've been signed out!");
    }

}
