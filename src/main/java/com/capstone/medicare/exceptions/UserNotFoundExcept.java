package com.capstone.medicare.exceptions;

public class UserNotFoundExcept extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundExcept() {
		super("User with this username not found in database");
	}

	public UserNotFoundExcept(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundExcept(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundExcept(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundExcept(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
