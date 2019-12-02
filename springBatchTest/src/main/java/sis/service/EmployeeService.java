package sis.service;

import java.util.List;

import sis.model.Employee;

public interface EmployeeService {

	List<Employee> listAllUsers();
	
	void insertEmployee(Employee employee);
}
