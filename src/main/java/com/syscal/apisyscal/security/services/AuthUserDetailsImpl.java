package com.syscal.apisyscal.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.syscal.apisyscal.model.entity.RolEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syscal.apisyscal.model.entity.UserEntity;

@Data
public class AuthUserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;
    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private RolEntity roles;

    private Collection<? extends GrantedAuthority> authorities;

    public AuthUserDetailsImpl(Integer id, String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities, RolEntity roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.roles = roles;
    }

    public static AuthUserDetailsImpl build(UserEntity user) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRol().getName()));

        return new AuthUserDetailsImpl(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), authorities, user.getRol());
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AuthUserDetailsImpl user = (AuthUserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
