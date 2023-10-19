package com.seba.springboot.ggym.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seba.springboot.ggym.app.dao.request.AuthRequest;
import com.seba.springboot.ggym.app.dao.response.JwtAuthenticationResponse;
import com.seba.springboot.ggym.app.services.AuthenticationService;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(
		origins = {"http://localhost:8100", "http://localhost:4200"}
)
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	private final AuthenticationService authenticationService;
	
	@PostMapping("/signup")
	public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody AuthRequest request) {
		try {
			return ResponseEntity.ok(authenticationService.signup(request));	
		} catch(ConstraintViolationException e) {
			return ResponseEntity.badRequest().body(
					JwtAuthenticationResponse
						.builder()
						.success(false)
						.message("Alguno de los campos no es correcto.")
						.token(null)
						.build()
				);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody AuthRequest request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}
	
	@PostMapping("/recover")
	public ResponseEntity<JwtAuthenticationResponse> recover(@RequestBody AuthRequest request) {
		return ResponseEntity.ok(authenticationService.recoverPass(request));
	}
	
}
