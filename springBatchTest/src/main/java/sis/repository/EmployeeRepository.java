package sis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sis.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
