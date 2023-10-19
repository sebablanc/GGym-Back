package com.seba.springboot.ggym.app.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.seba.springboot.ggym.app.entities.UserEntity;

public interface JwtService {
	String extractUserName(String token);

    String generateToken(UserEntity userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
