package com.paypal.bfs.test.employeeserv.exception;

public class EntityNofoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EntityNofoundException(String message) {
		super(message);
	}

}