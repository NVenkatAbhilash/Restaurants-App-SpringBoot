package com.zemoso.springbootassignment.restexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestaurantRestExceptionHandling {
	
	@ExceptionHandler
	public ResponseEntity<RestaurantErrorResponse> handleException(RestaurantNotFoundException exc){
		
		RestaurantErrorResponse error  = new RestaurantErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());		
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<RestaurantErrorResponse> handleException(Exception exc){
		
		RestaurantErrorResponse error  = new RestaurantErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());		
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
