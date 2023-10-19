package com.seba.springboot.ggym.app.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.seba.springboot.ggym.app.dao.response.RolesResponse;
import com.seba.springboot.ggym.app.entities.UserEntity;

public interface UserService {
	UserDetailsService userDetailsService();
	
	UserEntity loadUserByJWT(String jwt);
	
	RolesResponse userRoles(String jwt);
}
