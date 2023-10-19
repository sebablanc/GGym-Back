package com.seba.springboot.ggym.app.services.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seba.springboot.ggym.app.dao.request.AuthRequest;
import com.seba.springboot.ggym.app.dao.response.JwtAuthenticationResponse;
import com.seba.springboot.ggym.app.entities.UserEntity;
import com.seba.springboot.ggym.app.enums.Role;
import com.seba.springboot.ggym.app.repository.UserRepository;
import com.seba.springboot.ggym.app.services.AuthenticationService;
import com.seba.springboot.ggym.app.services.JwtService;
import com.seba.springboot.ggym.app.utils.DateUtils;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private AuthenticationManager authenticationManager;

	@Override
	public JwtAuthenticationResponse signup(AuthRequest request) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(Role.ADMIN.name()));
		Boolean success = false;
		String message = "Hubo un error al intentar crear tu usuario";
		String jwt = "";
		try {
			UserEntity user = UserEntity
					.builder()
					.email(request.getEmail())
					.password(passwordEncoder.encode(request.getPassword()))
					.authorities(authorities)
					.build();
			
			userRepository.save(user);
			
			jwt = jwtService.generateToken(user);
			
			success = true;
			
			message = "Usuario creado correctamente.";
			
		} catch(DataIntegrityViolationException e) {
			message = "El email proporcionado ya se encuentra en uso.";
		}
		
		
		return JwtAuthenticationResponse.builder()
				.success(success)
				.message(message)
				.token(jwt)
				.build();
	}

	@Override
	public JwtAuthenticationResponse signin(AuthRequest request) throws IllegalArgumentException {
		Boolean success = false;
		String message = "Correo y/o contraseña inválida";
		String jwt = null;
		Date expiration = null;
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			
			UserEntity user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Correo y/o contraseña inválida"));
			
			jwt = jwtService.generateToken(user);
			
			success = true;
			message = "Login realizado correctamente";
			expiration = DateUtils.expirationDate();
			
		} catch(Exception e) {} 
		
		return JwtAuthenticationResponse.builder()
				.success(success)
				.message(message)
				.token(jwt)
				.expirationIn(expiration)
				.build();
		
	}

	@Override
	public JwtAuthenticationResponse recoverPass(AuthRequest request) {
		// TODO Auto-generated method stub
		return JwtAuthenticationResponse.builder()
				.success(true)
				.message("Se ha enviado un mail a tu casilla de correo.")
				.build();
	}

}
