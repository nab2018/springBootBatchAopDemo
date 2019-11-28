package sis.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import sis.model.Employee;

public class NotificationListener extends JobExecutionListenerSupport {

	private static final Logger LOGGER = LogManager
			.getLogger(NotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public NotificationListener(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
    public void afterJob(final JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("!!! JOB FINISHED! Time to verify the results");
            List<Employee> employeeList = jdbcTemplate.query("SELECT * FROM employee", new employeeRowMapper());         
            for(Employee employee: employeeList) {
            	LOGGER.info("Found <" + employee + "> in the database.");
            }
        }
    }

	class employeeRowMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int rowNr) throws SQLException {
			Employee employee = new Employee(rs.getString("employeeFirstName"),
					rs.getString("employeeLastName"), rs.getInt("employeeAge"),
					rs.getInt("employeeSalary"));
			return employee;
		}
	}
}