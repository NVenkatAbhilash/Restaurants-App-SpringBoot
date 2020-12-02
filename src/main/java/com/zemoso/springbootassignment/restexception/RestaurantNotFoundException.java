package com.zemoso.springbootassignment.restexception;

public class RestaurantNotFoundException extends RuntimeException{

	public RestaurantNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestaurantNotFoundException(String message) {
		super(message);
	}

	public RestaurantNotFoundException(Throwable cause) {
		super(cause);
	}

}
