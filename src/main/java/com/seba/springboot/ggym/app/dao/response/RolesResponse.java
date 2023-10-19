package com.seba.springboot.ggym.app.dao.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RolesResponse extends HttpResponse {
	private List<String> roles;
	
	@Builder
    public RolesResponse(Boolean success, String message, List<String> roles) {
        super(success, message);
        this.roles = roles;
    }
}
