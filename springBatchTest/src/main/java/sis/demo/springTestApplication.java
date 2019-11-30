package sis.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sis.config.batchConfig;
import sis.model.Employee;
import sis.service.EmployeeService;
import sis.service.EmployeeServiceImpl;

@SpringBootApplication
public class springTestApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				batchConfig.class);
//		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
//		Job job = context.getBean("listEmployeesJob", Job.class);
//		JobParameters jobParameters = new JobParametersBuilder()
//				.toJobParameters();
		EmployeeService employeeService = context.getBean(EmployeeServiceImpl.class);
		Employee emp = new Employee("Nabil", "Jalmous", 37, 120000);
		employeeService.listAllUsers();
		employeeService.insertEmployee(emp);
//		try {
////			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
//		} catch (JobExecutionAlreadyRunningException e) {
//			e.printStackTrace();
//		} catch (JobRestartException e) {
//			e.printStackTrace();
//		} catch (JobInstanceAlreadyCompleteException e) {
//			e.printStackTrace();
//		} catch (JobParametersInvalidException e) {
//			e.printStackTrace();
//		} finally {
//			((AnnotationConfigApplicationContext) context).close();
//		}
		((AnnotationConfigApplicationContext) context).close();
	}
}