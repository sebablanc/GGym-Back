package com.seba.springboot.ggym.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.seba.springboot.ggym.app.dao.request.ClientRequest;
import com.seba.springboot.ggym.app.dao.response.ClienteResponse;
import com.seba.springboot.ggym.app.entities.ClienteEntity;
import com.seba.springboot.ggym.app.repository.ClienteRepository;
import com.seba.springboot.ggym.app.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public ClienteResponse save(ClientRequest client) {
		Boolean success = Boolean.FALSE;
		String message = "Hemos tenido un inconveniente al intentar crear el cliente";

		try {	
			ClienteEntity clientToSave = ClienteEntity
					.builder()
					.id(client.getId())
					.document(client.getDocument())
					.name(client.getName())
					.lastName(client.getLastName())
					.email(client.getEmail())
					.phone(client.getPhone())
					.plan(client.getPlan())
					.build();
			
			clienteRepository.save(clientToSave);
			
			message = client.getId() != null ? "El cliente se modificó" : "El cliente se ha creado";
			success = Boolean.TRUE;
		} catch(DataIntegrityViolationException e) {
			message = "Alguno/s de los datos proporcionados ya se encuentran en uso.";
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ClienteResponse.builder()
				.success(success)
				.message(message)
				.build();
	}

	@Override
	public ClienteResponse getById(ClientRequest client) {
		Boolean success = Boolean.FALSE;
		String message = "Hemos tenido un inconveniente al intentar crear el cliente";
		List<ClienteEntity> clientes = new ArrayList<ClienteEntity>();
		try {
			ClienteEntity clienteFinded = clienteRepository.findById(Integer.parseInt(client.getId().toString())).orElse(null);
			
			clientes.add(clienteFinded);
			message = "Cliente creado correctamente";
			success = Boolean.TRUE;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ClienteResponse.builder()
				.success(success)
				.message(message)
				.clients(clientes)
				.build();
	}

	@Override
	public ClienteResponse getAll() {
		List<ClienteEntity> clientes = new ArrayList<ClienteEntity>();
		clienteRepository.findAll().forEach(clientes::add);
		return ClienteResponse.builder()
				.success(true)
				.message("Clientes encontrados")
				.clients(clientes)
				.build();
	}

	@Override
	public ClienteResponse delete(ClientRequest client) {
		ClienteEntity clientToDelete = new ClienteEntity();
		clientToDelete.setId(client.getId());
		
		clienteRepository.delete(clientToDelete);
		return ClienteResponse.builder()
				.success(true)
				.message("El Cliente se eliminó correctamente.")
				.clients(null)
				.build();
	}
	
}
