package com.sis.batch.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sis.batch.model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {
	public Employee mapRow(ResultSet rs, int rowNr) throws SQLException {
		Employee employee = new Employee();
		employee.setEmployeeID(rs.getInt("employeeID"));
		employee.setEmployeeFirstName(rs.getString("employeeFirstName"));
		employee.setEmployeeLastName(rs.getString("employeeLastName"));
		employee.setEmployeeAge(rs.getInt("employeeAge"));
		employee.setEmployeeSalary(rs.getInt("employeeSalary"));
		return employee;
	}
}
