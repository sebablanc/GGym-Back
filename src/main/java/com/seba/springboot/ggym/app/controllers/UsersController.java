package com.seba.springboot.ggym.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seba.springboot.ggym.app.dao.response.RolesResponse;
import com.seba.springboot.ggym.app.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@CrossOrigin(
		origins = {"http://localhost:8100", "http://localhost:4200"}
)
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UsersController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/get_roles")
	public ResponseEntity<RolesResponse> getRoles(HttpServletRequest request) {
		final String authHeader = request.getHeader("Authorization");
		final String jwt = authHeader.substring(7);
		return ResponseEntity.ok(userService.userRoles(jwt));
	}
}
