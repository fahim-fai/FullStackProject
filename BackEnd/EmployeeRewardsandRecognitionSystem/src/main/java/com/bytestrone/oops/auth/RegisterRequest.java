package com.bytestrone.oops.auth;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	@NotNull(message = "name is required")
	private String name;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	
	private String phoneNumber;
	
}
