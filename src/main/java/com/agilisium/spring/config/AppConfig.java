package com.agilisium.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")
@ComponentScans(value = {@ComponentScan("com.agilisium.spring.service"),@ComponentScan("com.agilisium.spring.dao")})

public class AppConfig {
	
	@Autowired
	private Environment environment;  
	
	@Bean
	public DataSource getDatasource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("db.driver"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));
		
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactoryBean() {
		
		
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		//properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		
		factoryBean.setDataSource(getDatasource());
		factoryBean.setHibernateProperties(properties);
		factoryBean.setAnnotatedPackages("com.agilisium.spring.entity");
		factoryBean.setAnnotatedClasses(com.agilisium.spring.entity.ProductDetails.class);
		return factoryBean;
	}
	
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(getSessionFactoryBean().getObject());
		return hibernateTransactionManager;
				
	}

}
