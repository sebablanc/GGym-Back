package com.seba.springboot.ggym.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seba.springboot.ggym.app.dao.response.RolesResponse;
import com.seba.springboot.ggym.app.entities.UserEntity;
import com.seba.springboot.ggym.app.repository.UserRepository;
import com.seba.springboot.ggym.app.services.JwtService;
import com.seba.springboot.ggym.app.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
			}
		};
	}
	
	public UserEntity loadUserByJWT(String jwt) {
		String email = jwtService.extractUserName(jwt);
		UserEntity userFinded = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		
		userFinded.setPassword("");
		
		return userFinded;
	}

	@Override
	public RolesResponse userRoles(String jwt) {
		UserEntity userFinded = this.loadUserByJWT(jwt);
		List<String> roles = new ArrayList<>();
		userFinded.getAuthorities().forEach(auth -> {
			roles.add(auth.getAuthority());
		});
		return RolesResponse.builder()
				.success(true)
				.message("roles del usuario")
				.roles(roles)
				.build();
	}

}
