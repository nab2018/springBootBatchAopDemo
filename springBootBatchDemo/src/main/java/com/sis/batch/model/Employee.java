package com.sis.batch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "EMPLOYEEID")
	private long employeeID;
	@Column(name = "EMPLOYEEFIRSTNAME")
	private String employeeFirstName;
	@Column(name = "EMPLOYEELASTNAME")
	private String employeeLastName;
	@Column(name = "EMPLOYEEAGE")
	private int employeeAge;
	@Column(name = "EMPLOYEESALARY")
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