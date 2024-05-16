package com.bytestrone.oops.service;

import java.time.LocalDate;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bytestrone.oops.auth.AuthenticationRequest;
import com.bytestrone.oops.auth.Authenticationresponse;
import com.bytestrone.oops.auth.RegisterRequest;
import com.bytestrone.oops.dao.UserDao;
import com.bytestrone.oops.dto.ErrorResponse;
import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.model.Role;
import com.bytestrone.oops.model.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	// registering a user
	public ResponseEntity<ResponseWrapper<Authenticationresponse>> register(@Valid RegisterRequest request) {
//		String email = request.getEmail();
//		Optional<User> users = userDao.findByEmail(email);
//		if (users.isPresent()) { // to find if there is user present with same email
//			ErrorResponse errorResponse = new ErrorResponse("Email is already registered");
//			ResponseWrapper<Authenticationresponse> responseWrapper = new ResponseWrapper<>(null, errorResponse);
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseWrapper);
//		} else {
		try {
			User user = com.bytestrone.oops.model.User.builder().name(request.getName()).email(request.getEmail())
					.password(passwordEncoder.encode(request.getPassword())).role(Role.USER).points(0L)
					.acquiredPoints(0L).createdDate(LocalDate.now()).createdUser(request.getEmail())
					.phoneNumber(request.getPhoneNumber()).isActive(true).build();
			userDao.save(user);
			String jwtToken = jwtService.generateToken(user);
			Authenticationresponse authenticationresponse = Authenticationresponse.builder().token(jwtToken).build();
			ResponseWrapper<Authenticationresponse> responseWrapper = new ResponseWrapper<>(authenticationresponse,
					null);
			return ResponseEntity.ok(responseWrapper);
		}catch(DataIntegrityViolationException e) {
			ErrorResponse errorResponse = new ErrorResponse("Email is already registered");
			ResponseWrapper<Authenticationresponse> responseWrapper = new ResponseWrapper<>(null, errorResponse);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(responseWrapper);
		}
			
//		}

	}

	// login
	public ResponseEntity<ResponseWrapper<Authenticationresponse>> authenticate(AuthenticationRequest request) {
		String email = request.getEmail();
		if (userDao.returnUserByEmail(email) != null) {
			User testUser = userDao.returnUserByEmail(email);
			if (testUser.getIsActive()) {
				
				try {
				    authenticationManager.authenticate(
				            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
				    User user = userDao.findByEmail(request.getEmail()).orElseThrow();
					
					String jwtToken = jwtService.generateToken(user);
					Authenticationresponse authenticationResponse = Authenticationresponse.builder().token(jwtToken)
							.build();
					ResponseWrapper<Authenticationresponse> responseWrapper = new ResponseWrapper<>(authenticationResponse,
							null);
					return ResponseEntity.ok(responseWrapper);
				    
				} catch (org.springframework.security.core.AuthenticationException ex) {
						    
					ErrorResponse errorResponse = new ErrorResponse("Authentication failed");
				    ResponseWrapper<Authenticationresponse> responseWrapper = new ResponseWrapper<>(null, errorResponse);
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseWrapper);		    
				}
				
				
				
			} else {
				ErrorResponse errorResponse = new ErrorResponse("Employee Already Deleted");
				ResponseWrapper<Authenticationresponse> responseWrapper = new ResponseWrapper<>(null, errorResponse);
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseWrapper);
			}
		}

		else { //if there is no employee with given email
			ErrorResponse errorResponse = new ErrorResponse("No Employee with given Email");
			ResponseWrapper<Authenticationresponse> responseWrapper = new ResponseWrapper<>(null, errorResponse);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseWrapper);
		}
	}

}
