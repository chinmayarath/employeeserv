package com.paypal.bfs.test.employeeserv.impl;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EntityNofoundException;
import com.paypal.bfs.test.employeeserv.exception.UserExistException;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	@Autowired
	EmployeeService employeeService;

	@Override
	public ResponseEntity<Employee> employeeGetById(String id) {
		return new ResponseEntity<>(employeeService.getEmployeeById(Integer.valueOf(id)), HttpStatus.OK);
	}

	@Override
	public void createEmployee(Employee employee) {
		employeeService.saveEmployee(employee);
	}

	@ExceptionHandler(UserExistException.class)
	public ResponseEntity<ErrorMessage> handleContentNotAllowedException(UserExistException uee) {
		return new ResponseEntity<>(new ErrorMessage(uee.getMessage()), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleValidationException(ConstraintViolationException cve) {
		return new ResponseEntity<>(new ErrorMessage(cve.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNofoundException.class)
	public ResponseEntity<ErrorMessage> handleNotFoundException(EntityNofoundException enfe) {
		return new ResponseEntity<>(new ErrorMessage(enfe.getMessage()), HttpStatus.NOT_FOUND);
	}
}
