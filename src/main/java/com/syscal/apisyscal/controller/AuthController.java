package com.syscal.apisyscal.controller;

import javax.validation.Valid;

import com.syscal.apisyscal.email.EmailService;
import com.syscal.apisyscal.model.entity.EmailCodeEntity;
import com.syscal.apisyscal.model.request.RecoverPasswordDTO;
import com.syscal.apisyscal.model.request.ValidateCodeRequestDTO;
import com.syscal.apisyscal.security.services.AuthUserDetailsServiceImpl;
import com.syscal.apisyscal.service.EmailCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syscal.apisyscal.model.request.AuthRequestDTO;
import com.syscal.apisyscal.model.response.UserInfoResponseDTO;
import com.syscal.apisyscal.security.jwt.JwtUtils;
import com.syscal.apisyscal.security.services.AuthUserDetailsImpl;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthUserDetailsServiceImpl authUserDetailsService;

    @Autowired
    private EmailCodeService emailCodeService;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder encoder;
  
    @Autowired
    JwtUtils jwtUtils;
  
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequestDTO loginRequest) {
        log.info("Auth Controller - Login");
        //emailService.sendEmailTool("Inicio de sesion x", "carlostorres8791@gmail.com", "Prueba de correo");
        UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(userAuth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUserDetailsImpl userDetails = (AuthUserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        UserInfoResponseDTO response = new UserInfoResponseDTO(userDetails.getId(), userDetails.getName() ,userDetails.getUsername(), userDetails.getEmail(), jwtCookie.toString(),userDetails.getAuthorities(), userDetails.getRoles() );
        return ResponseEntity.ok().header("auth_token", jwtCookie.toString()).body(response);
    }

    @PostMapping("/recover-password")
    public ResponseEntity<?> recoverPassword(@Valid @RequestBody RecoverPasswordDTO body) {
        log.info("Auth Controller - Recover Password");
        authUserDetailsService.recoverPassword(body.getUsername());
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/validate-code")
    public ResponseEntity<?> validateCode(@Valid @RequestBody ValidateCodeRequestDTO body) {
        log.info("Auth Controller - Recover Password");
        EmailCodeEntity emailCode = emailCodeService.getOneByUsername(body.getUsername());
        if (!emailCode.getCode().equals(body.getCode())) {

        }
        return null;
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody RecoverPasswordDTO body ) throws Exception {
        log.info("Auth Controller - Change Password");
        String password = encoder.encode(body.getPassword());
        authUserDetailsService.updatePasswordBYUser(body.getUsername(),password);
        return  ResponseEntity.ok("User update with successful");
    }

    @PostMapping("/sign-out")
    public ResponseEntity<?> logoutUser() {
        log.info("Auth Controller - Sign Out");
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("You've been signed out!");
    }

}
