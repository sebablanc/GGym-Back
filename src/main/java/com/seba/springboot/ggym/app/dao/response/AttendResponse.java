package com.seba.springboot.ggym.app.dao.response;

import java.util.List;

import com.seba.springboot.ggym.app.entities.AttendEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AttendResponse extends HttpResponse {
	
	private List<AttendEntity> attends;
	
	@Builder
    public AttendResponse(Boolean success, String message, List<AttendEntity> attends) {
        super(success, message);
        this.attends = attends;
    }

}
