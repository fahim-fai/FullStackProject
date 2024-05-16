package com.bytestrone.oops.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bytestrone.oops.dao.UserDao;
import com.bytestrone.oops.dto.ErrorResponse;
import com.bytestrone.oops.dto.PasswordDto;
import com.bytestrone.oops.dto.ResponseWrapper;
import com.bytestrone.oops.model.User;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	//return user with certain email
	@Override
	public ResponseEntity<User> returnUserByEmail(String email) {
		
			User user = userDao.returnUserByEmail(email);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.notFound().build();
			}
		
	}

	//get users ordered by points
	@Override
	public ResponseEntity<List<User>> getUserPoints() {
		
			List<User> users = userDao.getUsersOrderByPoints();
			if (!users.isEmpty()) {
				return ResponseEntity.ok(users);
			} else {
				return ResponseEntity.noContent().build();
			}
		
	}

	//get list of every user
	@Override
	public ResponseEntity<List<User>> getUsers() {
		
			List<User> users = userDao.findAll();
			logger.info(" successfully retrieved");
			return new ResponseEntity<>(users, HttpStatus.OK);
		 
	}

	//return user with certain id
	@Override
	public ResponseEntity<User> returnUserById(Long id) {
		
			Optional<User> userOptional = userDao.findById(id);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				logger.info("User with ID {} retrieved successfully", id);
				return ResponseEntity.ok(user);
			} else {
				logger.warn("User with ID {} not found", id);
				return ResponseEntity.notFound().build();
			}
		
	}

	//delete user with given id
	@Override
	public void deleteUserById(Long id) {
		
			Optional<User> userOptional = userDao.findById(id);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				user.setIsActive(false);
				userDao.save(user);
				logger.info("User with ID {} deleted successfully", id);
			} else {
				logger.warn("User with ID {} not found", id);
			}
		
	}

	//To Get UserEmail from token
	public String getUserByToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user.getEmail();
	}

	//get user from token
	public User getUserDetailByToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user;
	}

	//to check if the user is admin from token
	@Override
	public Boolean userIsAdminByToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user.isAdmin();
	}

	//to get the list of active users
	@Override
	public ResponseEntity<List<User>> getActiveUsers() {
		
			List<User> users = userDao.getActiveUsers();
			logger.info("Retrieved active users successfully");
			return ResponseEntity.ok(users);
		
	}

	//to get the list of in active users
	@Override
	public ResponseEntity<List<User>> getInActiveUsers() {
		List<User> users = userDao.getInActiveUsers();
		logger.info("Retrieved inActive users successfully");
		return new ResponseEntity<List<User>>(users, HttpStatus.ACCEPTED);
		
	}

	//changing password of user
	@Override
	public ResponseEntity<ResponseWrapper<String>> changePassword(PasswordDto passwordDto) {
		
			User user = getUserDetailByToken();

			if (passwordEncoder.matches(passwordDto.getPassword(), user.getPassword())) {
				ErrorResponse errorResponse = new ErrorResponse("Password Should Be Different From Before");
				ResponseWrapper<String> responseWrapper = new ResponseWrapper<>(null, errorResponse);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseWrapper);
			} else {
				user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
				userDao.save(user);
				ResponseWrapper<String> responseWrapper = new ResponseWrapper<>("Password Changed Successfully", null);
				return ResponseEntity.ok(responseWrapper);
			}
		
	}

	//delete a past employee
	@Override
	public void deletePastUser(Long id) {
		
		Optional<User> userOptional = userDao.findById(id);
		if (userOptional.isPresent()) {
			userDao.deleteById(id);
			logger.info("User with ID {} deleted successfully", id);
		} else {
			logger.warn("User with ID {} not found", id);
		}
		
	}

}
