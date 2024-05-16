package com.bytestrone.oops.exceptionHandler;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bytestrone.oops.dto.ErrorResponse;


	@RestControllerAdvice
	public class ApplicationExceptionHandler {
		
		Logger logger= LoggerFactory.getLogger(ApplicationExceptionHandler.class);
		
		ErrorResponse defaultErrorResponse=new ErrorResponse("Something went wrong");
		
//		@ExceptionHandler(IndexOutOfBoundsException.class)
//		public HashMap<String, String> handleIndexOutOfBoundsException(IndexOutOfBoundsException e) {
//			HashMap<String, String> errorHandlingMap = new HashMap<>();
//			errorHandlingMap.put("error", "Index out of bounds exception occurred");
//			return errorHandlingMap;
//		}
		
		@ExceptionHandler(DataIntegrityViolationException.class)
	    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		 String detailMessage = e.getMessage();
		   
	            int startIndex = detailMessage.indexOf("(")+1;
	            int endIndex = detailMessage.indexOf(")");
	            String duplicateKeyInfo = detailMessage.substring(startIndex, endIndex  );
	            duplicateKeyInfo = duplicateKeyInfo + " already exists";
	            logger.error(duplicateKeyInfo);
	            ErrorResponse errorResponse=new ErrorResponse(duplicateKeyInfo);
	            return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
	        
	       
	    }
		
		@ExceptionHandler(NoSuchElementException.class)
	    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
		 String detailMessage = e.getMessage();
		 logger.error(detailMessage);
		 ErrorResponse errorResponse=new ErrorResponse(detailMessage);
		 return ResponseEntity.badRequest().body(errorResponse);
	       
	    }
		@ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
			String errorCode = e.getBindingResult().getAllErrors().get(0).getCode();
			String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
			logger.error(errorCode + ": " + errorMessage);
		 ErrorResponse errorResponse=new ErrorResponse(errorMessage);
		 return ResponseEntity.badRequest().body(errorResponse);
	       
	    }
//		@ExceptionHandler(UsernameNotFoundException.class)
//	    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UsernameNotFoundException e) {
//			logger.error("user not found");
//			ErrorResponse errorResponse=new ErrorResponse("User Not Found");
//			 return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);	       
//	    }
		@ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleAnyException(Exception e) {
			logger.error("Exception Occured");
		 return ResponseEntity.badRequest().body(defaultErrorResponse);
	       
	    }
		
	}
