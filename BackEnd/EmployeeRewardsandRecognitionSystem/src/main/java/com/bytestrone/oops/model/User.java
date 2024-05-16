package com.bytestrone.oops.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "user_details")
public class User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@Column(unique=true)
	private String email;
	
	@NotNull
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String phoneNumber;
	
	private Long points;
	
	private Long acquiredPoints;
	
	private boolean isActive;

	@Column(name = "created_date")
    private LocalDate createdDate;
	
	@Column(name="created_user")
	private String createdUser;
	
	@Column(name = "modified_date")
    private LocalDate modifiedDate;
	
	@Column(name="modified_user")
	private String modifiedUser;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	
	public void creationDate() {
		this.createdDate=LocalDate.now();
	}
	public void modifiedDate() {
		this.modifiedDate=LocalDate.now();
	}
	public void createdUser() {
		this.createdUser=this.email;
	}

	@Override
	public String getUsername() {
		return email;
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
	public boolean isAdmin() {
		return this.role.equals(Role.ADMIN);
	}
	public boolean isUser() {
		return this.role.equals(Role.USER);
	}
	public boolean getIsActive() {
		return this.isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive=isActive;
	}
}