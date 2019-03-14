package com.fashion.www.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.fashion.www.dao","com.fashion.www.dao.hotRecommend"})
public class RootConfig {
	@Bean
	public DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/fashion");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		dataSource.setInitialSize(10);
		dataSource.setMinIdle(5);
		dataSource.setMaxIdle(20);
		return dataSource;
	}
}
