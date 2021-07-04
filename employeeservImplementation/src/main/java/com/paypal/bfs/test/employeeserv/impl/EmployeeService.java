package com.paypal.bfs.test.employeeserv.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EntityNofoundException;
import com.paypal.bfs.test.employeeserv.exception.UserExistException;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll();
	}

	public Employee getEmployeeById(Integer id) {
		Optional<Employee> result = employeeRepo.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new EntityNofoundException("No employee found with  id " +  id);
		}
	}
	
	public void saveEmployee(Employee employee) {
		validateDuplicateEmployee(employee);
		employeeRepo.saveAndFlush(employee);
	}

	/**
	 * @throws UserExistException when user with same first name and last name exists
	 * @param employee
	 */
	private void validateDuplicateEmployee(Employee employee) {
		if (employeeRepo.getEmployeeBayName(employee.getFirstName(), employee.getLastName()) != null) {
			throw new UserExistException(employee.getFirstName(), employee.getLastName());
		}
		
	}

}
