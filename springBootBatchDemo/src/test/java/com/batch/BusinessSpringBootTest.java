package com.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BusinessSpringBootTest {

//	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	@Autowired
//	private NotificationListener notificationListener;
//	@Autowired
//	private EmployeeServiceImpl employeeService;

	// Conclusion: JdbcTemplate method is a lot faster than repository method
	@Test
	public void invokeJdbcTemplate() {
//		LocalDateTime start = LocalDateTime.now();
//		Date startDate = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());
//		List<Employee> employeeList = jdbcTemplate.query(
//				"SELECT * FROM EMPLOYEE", new EmployeeRowMapper());
//		for (Employee employee : employeeList) {
//			LOGGER.info("Found <" + employee.toString()
//					+ "> in the database.");
//		}
//		LocalDateTime end = LocalDateTime.now();
//		Date endDate = Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
//		LOGGER.info("!!! DBJOB FINISHED! JpaExecutionTime using jdbcTemplate is: " +
//					notificationListener.calculateExecutionTime(startDate, endDate)
//					+ ".");
	}

	@Test
	public void invokeJpaRepository() {
//		LocalDateTime start = LocalDateTime.now();
//		Date startDate = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());
//		List<Employee> employeeList = employeeService.listAllUsers();
//		for (Employee employee : employeeList) {
//			LOGGER.info("Found <" + employee.toString()
//					+ "> in the database.");
//		}
//		LocalDateTime end = LocalDateTime.now();
//		Date endDate = Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
//		LOGGER.info("!!! DBJOB FINISHED! JpaExecutionTime using jpaRepository is: " +
//					notificationListener.calculateExecutionTime(startDate, endDate)
//					+ ".");
	}
}