package com.seba.springboot.ggym.app.dao.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JwtAuthenticationResponse extends HttpResponse {
	private String token;
	
	private Date expirationIn;
	
	@Builder
    public JwtAuthenticationResponse(Boolean success, String message, String token, Date expirationIn) {
        super(success, message);
        this.token = token;
        this.expirationIn = expirationIn;
    }
}
