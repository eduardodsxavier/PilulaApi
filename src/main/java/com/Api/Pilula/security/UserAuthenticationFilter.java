package com.Api.Pilula.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Api.Pilula.model.Usuario;
import com.Api.Pilula.repository.UsuarioRepository;
import com.Api.Pilula.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService service;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter) throws ServletException, IOException {
        if(endpointNotPublic(request)) {
            String token = service.recoveryToken(request);
            if (token == null) {throw new RuntimeException("absent token");}
            String subject = service.getSubjectFromToken(token);
            Usuario user = repository.findByCpf(subject).get();
            UserDetailsImpl userDetails = new UserDetailsImpl(user);

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filter.doFilter(request, response);
    }
    
    private boolean endpointNotPublic(HttpServletRequest request) {
        return !Arrays.asList(SecurityConfig.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(request.getRequestURI());
    }
}
