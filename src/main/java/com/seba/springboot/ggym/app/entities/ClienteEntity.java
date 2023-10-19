package com.seba.springboot.ggym.app.entities;

import java.io.Serial;

import com.seba.springboot.ggym.app.enums.PlanType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteEntity extends BaseEntity {

	/**
     * 
     */
    @Serial
    private static final long serialVersionUID = -467324267912994552L;
    
    @Column(nullable = false)
	@NonNull
	private String name;
	
    @Column(nullable = false)
	@NonNull
	private String lastName;
	
	@Column(unique = true, nullable = false)
	@NonNull
	private Integer document;

	@Column(unique = true, nullable = false)
	@NonNull
	private String email;
	
	private String phone;
	
	@Enumerated(value = EnumType.STRING)
	private PlanType plan;
	
	@Builder
	private ClienteEntity(Long id, @NonNull String name, @NonNull String lastName, @NonNull Integer document,
			@NonNull String email, String phone, PlanType plan) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.document = document;
		this.email = email;
		this.phone = phone;
		this.plan = plan;
	}
	
}
