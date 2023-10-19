package com.seba.springboot.ggym.app.services;

import com.seba.springboot.ggym.app.dao.request.ClientRequest;
import com.seba.springboot.ggym.app.dao.response.ClienteResponse;

public interface ClientService {
	ClienteResponse save(ClientRequest client);
	
	ClienteResponse getById(ClientRequest client);
	
	ClienteResponse getAll();
	
	ClienteResponse delete(ClientRequest client);
}
