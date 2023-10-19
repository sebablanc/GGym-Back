package com.seba.springboot.ggym.app.controllers;

import java.util.Date;

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

import com.seba.springboot.ggym.app.dao.request.AttendRequest;
import com.seba.springboot.ggym.app.dao.response.AttendResponse;
import com.seba.springboot.ggym.app.services.AttendService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(
		origins = {"http://localhost:8100", "http://localhost:4200"}
)
@RestController
@RequestMapping("/api/v1/attends")
@RequiredArgsConstructor
public class AttendsController {
	
	@Autowired
	private AttendService attendService;

	@PostMapping("/save")
	public ResponseEntity<AttendResponse> save(@RequestBody AttendRequest request) {
		return ResponseEntity.ok(attendService.save(request));
	}
	
	@GetMapping("/get_by_client")
	public ResponseEntity<AttendResponse> getByClient(@RequestParam(required=false) Integer dni) {
		AttendRequest request = AttendRequest.builder()
				.dni(dni)
				.build();
		return ResponseEntity.ok(attendService.getByClient(request));
	}
	
	
	@GetMapping("/get_by_client_and_date")
	public ResponseEntity<AttendResponse> getByClientAndMonth(@RequestParam(required=false) Integer dni, @RequestParam(required=false) Date initialDate, @RequestParam(required=false) Date finalDate) {
		System.out.println(initialDate);
		AttendRequest request = AttendRequest.builder()
				.dni(dni)
				.fechaInicial(initialDate)
				.fechaFinal(finalDate)
				.build();
		return ResponseEntity.ok(attendService.getByClientAndDateBetween(request));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<AttendResponse> delete(@RequestParam(required=false) Long id) {
		try {
			AttendRequest request = AttendRequest.builder()
					.id(id)
					.build();
			return ResponseEntity.ok(attendService.delete(request));
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
}
