package com.syscal.apisyscal.model.response;

import com.syscal.apisyscal.model.entity.MainEntity;
import com.syscal.apisyscal.model.entity.RolEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private Integer id;
	private String username;
	private String email;
	private String auth_token;
	private Collection<? extends GrantedAuthority> authorities;
	private RolEntity roles;
}
