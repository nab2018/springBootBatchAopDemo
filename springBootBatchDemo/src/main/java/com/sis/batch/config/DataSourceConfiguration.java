package com.sis.batch.config;

import javax.sql.DataSource;

import org.h2.security.SHA256;
import org.h2.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfiguration {

	@Bean
	public DataSource dataSource() {
		String db_hashed_pass = StringUtils.convertBytesToHex(SHA256.getKeyPasswordHash("ADMINISTRATOR", "admin1829".toCharArray()));
		//String file_hashed_pass = StringUtils.convertBytesToHex(SHA256.getHash("consultants".getBytes(), true));
		//String pwds = file_hashed_pass + " " + db_hashed_pass;
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setJdbcUrl("jdbc:h2:~/springBootBatchDemo;PASSWORD_HASH=TRUE");
		dataSource.setUsername("administrator");
		dataSource.setPassword(db_hashed_pass);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
}