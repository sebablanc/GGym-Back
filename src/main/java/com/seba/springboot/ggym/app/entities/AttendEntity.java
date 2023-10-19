package com.seba.springboot.ggym.app.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attend")
public class AttendEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable = false)
	private Integer dni;
	
	@NotNull
	@Column(nullable = false)
	private Date attendDate;
	
	@Builder
	private AttendEntity(Long id, Integer dni, Date attendDate) {
		this.id = id;
		this.dni = dni;
		this.attendDate = attendDate;
	}
}
