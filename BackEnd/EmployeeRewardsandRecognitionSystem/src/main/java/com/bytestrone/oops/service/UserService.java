package com.bytestrone.oops.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bytestrone.oops.dto.PasswordDto;
import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.model.User;

public interface UserService {

	ResponseEntity<User> returnUserByEmail(String email);


	ResponseEntity<List<User>> getUserPoints();

	ResponseEntity<List<User>> getUsers();
	
	ResponseEntity<User> returnUserById(Long id);

	void deleteUserById(Long id);
	
	String getUserByToken();
	
	Boolean userIsAdminByToken();
	
	User getUserDetailByToken();


	ResponseEntity<List<User>> getActiveUsers();


	ResponseEntity<List<User>> getInActiveUsers();


	ResponseEntity<ResponseWrapper<String>> changePassword(PasswordDto passwordDto);


	void deletePastUser(Long id);
	

}
