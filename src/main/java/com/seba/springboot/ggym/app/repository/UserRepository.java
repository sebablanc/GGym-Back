package com.seba.springboot.ggym.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seba.springboot.ggym.app.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	Optional<UserEntity> findByEmail(String email);
}
