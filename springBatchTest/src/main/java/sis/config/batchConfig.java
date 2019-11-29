package sis.config;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import sis.model.Employee;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableBatchProcessing
public class batchConfig {
	@Autowired
	private JobBuilderFactory jobFactory;
	@Autowired
	private StepBuilderFactory stepFactory;

	public batchConfig() {
		BasicConfigurator.configure();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Employee> writer) {
		return stepFactory.get("step1").<Employee, Employee> chunk(5)
				.reader(employeeItemReader())
				.processor(employeeItemProcessor())
				.writer(writer)
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Job listEmployeesJob(Step step1) {
		return jobFactory.get("listEmployeesJob").listener(notificationListener()).start(step1).build();
	}

	@Bean
	public ItemReader<Employee> employeeItemReader() {
		FlatFileItemReader<Employee> reader = new FlatFileItemReader<Employee>();
		reader.setResource(new ClassPathResource("employees.csv"));
		DefaultLineMapper<Employee> mapper = new DefaultLineMapper<Employee>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(new String[] { "employeeFirstName",
				"employeeLastName", "employeeAge", "employeeSalary" });
		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<Employee>();
		fieldSetMapper.setTargetType(Employee.class);
		mapper.setLineTokenizer(tokenizer);
		mapper.setFieldSetMapper(fieldSetMapper);
		reader.setLineMapper(mapper);
		return reader;
	}

	@Bean
	public ItemProcessor<Employee, Employee> employeeItemProcessor() {
		return new ItemProcessor<Employee, Employee>() {
			public Employee process(Employee emp) throws Exception {
				Employee employee = (Employee) emp;
				employee.setEmployeeFirstName(employee.getEmployeeFirstName()
						.toUpperCase());
				employee.setEmployeeLastName(employee.getEmployeeLastName()
						.toUpperCase());
				return employee;
			}
		};
	}

	@Bean
	public JdbcBatchItemWriter<Employee> employeeItemWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Employee>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>())
				.sql("INSERT INTO employee(employeeFirstName, employeeLastName, employeeAge, employeeSalary) values(:employeeFirstName,:employeeLastName,:employeeAge,:employeeSalary)")
				.dataSource(dataSource)
				.build();
	}

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setJdbcUrl("jdbc:h2:file:~/test");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NotificationListener notificationListener() {
		return new NotificationListener(jdbcTemplate(dataSource()));
	}

	@Bean
	public TracePerformanceAspect tracePerformanceAspect() {
		return new TracePerformanceAspect();
	}
}