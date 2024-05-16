package com.bytestrone.oops.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytestrone.oops.model.User;

@RestController
@RequestMapping("/api/v1/userbytoken")
@CrossOrigin(origins = "*")
public class DemoController {
	

	@GetMapping
	public ResponseEntity<User> returnUserByToken(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    User user = (User) authentication.getPrincipal();
		return ResponseEntity.ok(user);
	}
	
	
}
