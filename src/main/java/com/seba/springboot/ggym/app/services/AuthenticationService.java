package com.seba.springboot.ggym.app.services;

import com.seba.springboot.ggym.app.dao.request.AuthRequest;
import com.seba.springboot.ggym.app.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
	JwtAuthenticationResponse signup(AuthRequest request);

    JwtAuthenticationResponse signin(AuthRequest request);
    
    JwtAuthenticationResponse recoverPass(AuthRequest request);
}
