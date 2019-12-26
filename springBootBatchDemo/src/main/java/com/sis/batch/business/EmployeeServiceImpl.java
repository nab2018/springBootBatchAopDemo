package com.sis.batch.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sis.batch.dao.EmployeeRepository;
import com.sis.batch.model.Employee;
import com.sis.batch.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	private List<Employee> employees = new ArrayList<Employee>();

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> listAllUsers() {
		employees = employeeRepository.findAll();
		return employees;
	}

	public void insertEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}
