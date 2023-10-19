package com.seba.springboot.ggym.app.services;

import com.seba.springboot.ggym.app.dao.request.AttendRequest;
import com.seba.springboot.ggym.app.dao.response.AttendResponse;

public interface AttendService {
	AttendResponse save(AttendRequest attend);
	
	AttendResponse getByClient(AttendRequest attend);
	
	AttendResponse getByClientAndDateBetween(AttendRequest attend);
	
	AttendResponse delete(AttendRequest attend);
}
