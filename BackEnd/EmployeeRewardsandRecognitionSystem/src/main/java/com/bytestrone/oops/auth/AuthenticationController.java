package com.bytestrone.oops.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService; 
	
	
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/register")
	public ResponseEntity<ResponseWrapper<Authenticationresponse>> register(
			
			
			@Valid @RequestBody RegisterRequest request
	){
		
		
		return authenticationService.register(request);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/authenticate")
	public ResponseEntity<ResponseWrapper<Authenticationresponse>> register(
			@RequestBody AuthenticationRequest request
	){
		
		return authenticationService.authenticate(request);
	}
	
	
	
	

}
