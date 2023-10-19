package com.seba.springboot.ggym.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.seba.springboot.ggym.app.entities.AttendEntity;

public interface AttendRepository extends CrudRepository<AttendEntity, Integer>{
	List<AttendEntity> findByDni(Integer dni);
	
	List<AttendEntity> findByDniAndAttendDateBetween(Integer dni, Date attendDateStart, Date attendDateEnd);
}
