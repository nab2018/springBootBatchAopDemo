package com.batch.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.batch.model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {
	public Employee mapRow(ResultSet rs, int rowNr) throws SQLException {
		Employee employee = new Employee(rs.getInt("employeeID"),
				rs.getString("employeeFirstName"),
				rs.getString("employeeLastName"), rs.getInt("employeeAge"),
				rs.getInt("employeeSalary"));
		return employee;
	}
}
