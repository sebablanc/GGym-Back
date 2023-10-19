package com.seba.springboot.ggym.app.dao.request;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendRequest {

	private Long id;
	
	@NotNull
	private Integer dni;
	
	private Date fechaInicial;
	
	private Date fechaFinal;
}
