package sis.config;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
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

import sis.model.Employee;

@Configuration
@EnableBatchProcessing
public class batchConfig {
	@Autowired
	private JobBuilderFactory jobFactory;
	@Autowired
	private StepBuilderFactory stepFactory;
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public batchConfig() {
		BasicConfigurator.configure();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Employee> writer) {
		return stepFactory.get("step1").<Employee, Employee> chunk(2)
				.reader(employeeItemReader())
				.processor(employeeItemProcessor())
				.writer(writer).build();
	}

	@Bean
	public Job listEmployeesJob(Step step1) {
		return jobFactory.get("listEmployeesJob").start(step1).build();
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
		try {
			logger.info("[employeeItemReader]:" + "[ Name: "
					+ reader.read().getEmployeeFirstName() + " "
					+ reader.read().getEmployeeLastName() + ", Age: "
					+ reader.read().getEmployeeAge() + ", Salary"
					+ reader.read().getEmployeeSalary() + " ]");
		} catch (UnexpectedInputException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reader;
	}

	@Bean
	public ItemProcessor<Employee, Employee> employeeItemProcessor() {
		return new ItemProcessor<Employee, Employee>() {
			public Employee process(Employee arg0) throws Exception {
				Employee employee = (Employee) arg0;
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
}