package com.batch.dao;
package com.sis.batch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch.model.Employee;
import com.sis.batch.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
