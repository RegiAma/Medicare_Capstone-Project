package com.capstone.medicare.exceptions;

public class UserFoundExcept extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserFoundExcept() {
		super("User with this username is already there in database!! Try with another username");
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public UserFoundExcept(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserFoundExcept(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserFoundExcept(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
