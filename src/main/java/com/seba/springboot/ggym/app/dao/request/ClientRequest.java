package com.seba.springboot.ggym.app.dao.request;

import com.seba.springboot.ggym.app.enums.PlanType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
	private Long id;
	private Integer document;
	private String name;
	private String lastName;
	private String email;
	private String phone;
	private PlanType plan;
}
