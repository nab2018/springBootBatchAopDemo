package com.batch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private long employeeID;
	private String employeeFirstName;
	private String employeeLastName;
	private int employeeAge;
	private int employeeSalary;

	public Employee() {	}
	
	public Employee(String employeeFirstName, String employeeLastName, int employeeAge, int employeeSalary) {
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.employeeAge = employeeAge;
		this.employeeSalary = employeeSalary;
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}

	public int getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(int employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	
	@Override
	public String toString() {
		return "[ employeeID: " + this.employeeID + ", employeeName: " + this.employeeFirstName 
				+ " " + this.employeeLastName + ", employeeAge: " + this.employeeAge +
				", employeeSalary: " + this.employeeSalary + " ]";
	}
}