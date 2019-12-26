package com.sis.batch.service;

import java.util.List;

import com.batch.model.Employee;
import com.sis.batch.model.Employee;

public interface EmployeeService {

	List<Employee> listAllUsers();
	
	void insertEmployee(Employee employee);
}
