package com.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch.dao.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
