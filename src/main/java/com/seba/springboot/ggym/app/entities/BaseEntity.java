package com.seba.springboot.ggym.app.entities;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -7363399724812884337L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if(!this.getClass().isInstance(obj)) return false;
		
		BaseEntity other = (BaseEntity) obj;
		
		return id != null && id.equals(other.getId());
	}
}
