package com.seba.springboot.ggym.app.dao.response;

import com.seba.springboot.ggym.app.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponse extends HttpResponse {
	private UserEntity user;
	
	@Builder
    public UserResponse(Boolean success, String message, UserEntity user) {
        super(success, message);
        this.user = user;
    }
}
