package com.batch.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.batch.model.Employee;

@Component
public class NotificationListener extends JobExecutionListenerSupport {
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplate;

	public NotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(final JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			String totalExecutionTime = calculateExecutionTime(
					jobExecution.getCreateTime(), jobExecution.getEndTime());
			LOGGER.info("!!! JOB FINISHED! JobExecutionTime is: " + totalExecutionTime
					+ ". Time to verify the results:");
			List<Employee> employeeList = jdbcTemplate.query(
					"SELECT * FROM EMPLOYEE", new employeeRowMapper());
			for (Employee employee : employeeList) {
				LOGGER.info("Found <" + employee.toString()
						+ "> in the database.");
			}
		}
	}

	public String calculateExecutionTime(Date creationTime, Date endTime) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant creationInstant = creationTime.toInstant();
		Instant endInstant = endTime.toInstant();
		LocalDateTime creationDateTime = creationInstant.atZone(defaultZoneId)
				.toLocalDateTime();
		LocalDateTime endDateTime = endInstant.atZone(defaultZoneId)
				.toLocalDateTime();
		LocalDateTime tempDateTime = LocalDateTime.from(creationDateTime);
		long years = tempDateTime.until(endDateTime, ChronoUnit.YEARS);
		tempDateTime = tempDateTime.plusYears(years);
		long months = tempDateTime.until(endDateTime, ChronoUnit.MONTHS);
		tempDateTime = tempDateTime.plusMonths(months);
		long days = tempDateTime.until(endDateTime, ChronoUnit.DAYS);
		tempDateTime = tempDateTime.plusDays(days);
		long hours = tempDateTime.until(endDateTime, ChronoUnit.HOURS);
		tempDateTime = tempDateTime.plusHours(hours);
		long minutes = tempDateTime.until(endDateTime, ChronoUnit.MINUTES);
		tempDateTime = tempDateTime.plusMinutes(minutes);
		long seconds = tempDateTime.until(endDateTime, ChronoUnit.SECONDS);
		long milliSeconds = tempDateTime.until(endDateTime, ChronoUnit.MILLIS);
		String executionDateTime = years + " years, " + months + " months, " + days
				+ " days, " + hours + " hours, " + minutes + " minutes, "
				+ seconds + " seconds, " + milliSeconds + " milliseconds.";
		return executionDateTime;
	}

	class employeeRowMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet rs, int rowNr) throws SQLException {
			Employee employee = new Employee(rs.getInt("employeeID"),
					rs.getString("employeeFirstName"),
					rs.getString("employeeLastName"), rs.getInt("employeeAge"),
					rs.getInt("employeeSalary"));
			return employee;
		}
	}
}