package com.Api.Pilula.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Api.Pilula.model.Usuario;

public class UserDetailsImpl implements UserDetails {

    private Usuario user;

    public UserDetailsImpl(Usuario user) {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_AUTHENTICATED"));
    }

    public Usuario getUser() {
        return user;
    }

    public String getId() {
        return user.cpf();
    }

    @Override
    public String getPassword() {
        return user.senha();
    }

    @Override
    public String getUsername() {
        return user.cpf();
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
}
