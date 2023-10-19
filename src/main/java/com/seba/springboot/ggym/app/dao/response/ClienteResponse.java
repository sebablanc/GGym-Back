package com.seba.springboot.ggym.app.dao.response;

import java.util.List;

import com.seba.springboot.ggym.app.entities.ClienteEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClienteResponse extends HttpResponse {
	private List<ClienteEntity> clients;
	
	@Builder
    public ClienteResponse(Boolean success, String message, List<ClienteEntity> clients) {
        super(success, message);
        this.clients = clients;
    }
}
