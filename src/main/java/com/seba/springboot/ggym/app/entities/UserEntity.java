package com.seba.springboot.ggym.app.entities;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity implements UserDetails {
	
	/**
     * 
     */
    @Serial
    private static final long serialVersionUID = -467324267912994552L;
    
    @Column(unique = true)
	@NonNull
	@Email
	private String email;

	@NonNull
	private String password;

	private Set<GrantedAuthority>  authorities;
	
	@Builder
	private UserEntity(Long id, @NonNull String email, @NonNull String password, Set<GrantedAuthority>  authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
