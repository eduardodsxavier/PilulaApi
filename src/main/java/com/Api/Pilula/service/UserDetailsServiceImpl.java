package com.Api.Pilula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Api.Pilula.repository.UsuarioRepository;
import com.Api.Pilula.security.UserDetailsImpl;
import com.Api.Pilula.model.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Usuario user = repository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("User not find"));
        return new UserDetailsImpl(user);
    }
    
}
