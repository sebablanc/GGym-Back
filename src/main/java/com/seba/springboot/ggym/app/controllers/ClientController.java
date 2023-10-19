package com.seba.springboot.ggym.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seba.springboot.ggym.app.dao.request.ClientRequest;
import com.seba.springboot.ggym.app.dao.response.ClienteResponse;
import com.seba.springboot.ggym.app.services.ClientService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(
		origins = {"http://localhost:8100", "http://localhost:4200"}
)
@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/save")
	public ResponseEntity<ClienteResponse> save(@RequestBody ClientRequest request) {
		return ResponseEntity.ok(clientService.save(request));
	}
	
	@GetMapping("/get_one_client")
	public ResponseEntity<ClienteResponse> getOneClient(@RequestParam String id) {
		ClientRequest client = new ClientRequest();
		client.setId(Long.parseLong(id));
		return ResponseEntity.ok(clientService.getById(client));
	}
	
	@GetMapping("/get_all")
	public ResponseEntity<ClienteResponse> getAll() {
		return ResponseEntity.ok(clientService.getAll());
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ClienteResponse> delete(@RequestParam(required=false) String id) {
		ClientRequest client = new ClientRequest();
		client.setId(Long.parseLong(id));
		return ResponseEntity.ok(clientService.delete(client));
	}
}
