package com.demo.configurations;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.demo.serviceClass.userDetailsServiceImpl;

@Configuration
@ComponentScan("com")
public class AppConfig {
	
	@Bean
	InternalResourceViewResolver viewResolver()
	{
		InternalResourceViewResolver viewObj = new InternalResourceViewResolver();
		viewObj.setPrefix("/WEB-INF/views/");
		viewObj.setSuffix(".jsp");
		return viewObj;
	}
	
	@Bean
	public DataSource datasource()
	{
		DriverManagerDataSource obj=new DriverManagerDataSource();
		obj.setUsername("root");
		obj.setPassword("root");
		obj.setUrl("jdbc:mysql://localhost:3306/sampledb");
		obj.setDriverClassName("com.mysql.cj.jdbc.Driver");
				
		return obj;
	}
	
	
	
	
//	@Bean
//	public JdbcUserDetailsManager jdbcUserDetailsManagerBean()
//	{
//		return new JdbcUserDetailsManager(datasource());
//	}
	
	@Bean
	public userDetailsServiceImpl userServiceImplBean()
	{
		return  new userDetailsServiceImpl();
	}
	
	
	@Bean
	public JdbcTemplate jdbcTemplateBean()
	{
		return new JdbcTemplate(datasource());
	}

}
