package com.batch.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.batch.dao.EmployeeRepository;
import com.batch.model.Employee;
import com.batch.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
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
