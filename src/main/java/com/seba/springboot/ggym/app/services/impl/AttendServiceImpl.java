package com.seba.springboot.ggym.app.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.seba.springboot.ggym.app.dao.request.AttendRequest;
import com.seba.springboot.ggym.app.dao.response.AttendResponse;
import com.seba.springboot.ggym.app.entities.AttendEntity;
import com.seba.springboot.ggym.app.entities.ClienteEntity;
import com.seba.springboot.ggym.app.repository.AttendRepository;
import com.seba.springboot.ggym.app.repository.ClienteRepository;
import com.seba.springboot.ggym.app.services.AttendService;

@Service
public class AttendServiceImpl implements AttendService {

	@Autowired
	private AttendRepository attendRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public AttendResponse save(AttendRequest attendSended) {
		Boolean success = Boolean.FALSE;
		String message = "No existe un cliente con el DNI que proporcionaste";
		
		try {
			Integer dni = attendSended.getDni();
			ClienteEntity cliente = clienteRepository.findByDocument(dni);
			
			if(cliente != null && cliente.getDocument().equals(dni)) {				
				AttendEntity attendToSave = AttendEntity.builder()
						.dni(dni)
						.attendDate(new Date())
						.build();
				
				attendRepository.save(attendToSave);
				success = Boolean.TRUE;
				message = "La asistencia se guardó";
			} else {
				message = "No existe un cliente con el DNI que proporcionaste";
			}
			
			
		} catch(DataIntegrityViolationException e) {
			message = "Alguno/s de los datos proporcionados ya se encuentran en uso.";
		} catch(Exception e) {
			System.out.println(e.getMessage());
			message = "Hemos tenido un inconveniente al intentar guardar la asistencia";
		}
		return AttendResponse.builder()
				.success(success)
				.message(message)
				.build();
	}

	@Override
	public AttendResponse delete(AttendRequest attend) {
		AttendEntity attendToDelete = AttendEntity.builder()
				.id(attend.getId())
				.build();
		attendRepository.delete(attendToDelete);
		return AttendResponse.builder()
				.success(true)
				.message("Eliminaste la asistencia correctamente")
				.build();
	}

	@Override
	public AttendResponse getByClient(AttendRequest attend) {
		List<AttendEntity> attends = attendRepository.findByDni(attend.getDni());
		return AttendResponse.builder()
				.success(true)
				.message("Asistencias obtenidas")
				.attends(attends)
				.build();
	}

	@Override
	public AttendResponse getByClientAndDateBetween(AttendRequest attend) {
		List<AttendEntity> attends = attendRepository.findByDniAndAttendDateBetween(attend.getDni(), attend.getFechaInicial(), attend.getFechaFinal());
		return AttendResponse.builder()
				.success(true)
				.message("Asistencias obtenidas")
				.attends(attends)
				.build();
	}

}
