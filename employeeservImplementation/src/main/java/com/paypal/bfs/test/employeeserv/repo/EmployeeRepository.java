package com.paypal.bfs.test.employeeserv.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("select e from Employee e where e.firstName = :firstName and e.lastName = :lastName")
	Employee getEmployeeBayName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
