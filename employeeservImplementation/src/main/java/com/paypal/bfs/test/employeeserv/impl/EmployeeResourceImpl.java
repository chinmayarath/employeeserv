package com.paypal.bfs.test.employeeserv.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
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
	public ResponseEntity<?> handleContentNotAllowedException(UserExistException uee) {
		return new ResponseEntity<>(uee.getMessage(), HttpStatus.CONFLICT);
	}
}
