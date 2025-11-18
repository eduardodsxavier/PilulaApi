package com.Api.Pilula.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.Api.Pilula.security.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
    private static final String SECRET_KEY = "d386d2f1326bc8b595cab8cfe81cdb435c086fe3c1d7ac8d146487ec1c79bd0126baaf485592da4a13fbb71e25b0e8972205ee9d34ec9951d5c1e1cb84689bba";
    private static final String ISSUER = "AUTHRBAC";

    public String generateToken(UserDetailsImpl user) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(creationDate())
                .withExpiresAt(expirationDate())
                .withSubject(user.getUsername())
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String getSubjectFromToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("invalid or expired token");
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("GMT-3")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("GMT-3")).plusMinutes(30).toInstant();
    }

    public String getSubjectFromRequest(HttpServletRequest request) {
        String token = recoveryToken(request);
        return getSubjectFromToken(token);
    }

    public String recoveryToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        System.out.println("HEADER RECEBIDO = " + header);
        return header == null ? null : header.replace("Bearer ", "");
    }
}
