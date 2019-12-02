package com.batch.service;

import java.util.List;

import com.batch.dao.Employee;

public interface EmployeeService {

	List<Employee> listAllUsers();
	
	void insertEmployee(Employee employee);
}
