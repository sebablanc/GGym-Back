package com.seba.springboot.ggym.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.seba.springboot.ggym.app.entities.ClienteEntity;

public interface ClienteRepository extends CrudRepository<ClienteEntity, Integer> {

	ClienteEntity findByDocument(Integer document);
}
