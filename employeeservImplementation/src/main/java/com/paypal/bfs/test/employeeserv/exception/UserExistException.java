package com.paypal.bfs.test.employeeserv.exception;

public class UserExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserExistException(String fname, String lname) {
		super("User with firstName " + fname + " and last name " + lname +" already exists.");
	}

}
