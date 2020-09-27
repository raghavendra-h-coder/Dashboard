package com.shortlist.emailsdashboard.Server;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.shortlist.emailsdashboard.Repository.DatabaseSessionManager;



@SpringBootApplication
@EnableJpaRepositories("com.shortlist.emailsdashboard.Repository")
@ComponentScan({"com.shortlist.emailsdashboard.*"})
public class EmailsDashboardServer {
	
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";

	public static void main(String[] args) {
		SpringApplication.run(EmailsDashboardServer.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/emailsdashboard?verifyServerCertificate=false&useSSL=false&requireSSL=false");
		datasource.setUsername("master");
		datasource.setPassword("master");
		return datasource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		em.setPackagesToScan("com.shortlist.emailsdashboard.*");
		em.setPersistenceUnitName("portalentitymanager");
		em.setJpaProperties(hibProperties());
		return em;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();


		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, "org.hibernate.dialect.MySQLDialect");
//        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, dbProps.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
//		properties.put(PROPERTY_NAME_HIBERNATE_CACHE_USE_QUERY_CACHE,
//				dbProps.getProperty(PROPERTY_NAME_HIBERNATE_CACHE_USE_QUERY_CACHE));
//		properties.put(PROPERTY_NAME_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE,
//				dbProps.getProperty(PROPERTY_NAME_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
//		properties.put(PROPERTY_NAME_HIBERNATE_CACHE_PROVIDER_CLASS,
//				dbProps.getProperty(PROPERTY_NAME_HIBERNATE_CACHE_PROVIDER_CLASS));
//		properties.put(PROPERTY_NAME_HIBERNATE_REGION_FACTORY_CLASS,
//				dbProps.getProperty(PROPERTY_NAME_HIBERNATE_REGION_FACTORY_CLASS));
//		properties.put(PROPERTY_NAME_EHCACHE_CONFIG_RESOURCE_NAME,
//				dbProps.getProperty(PROPERTY_NAME_EHCACHE_CONFIG_RESOURCE_NAME));
//
//		properties.put(PROPERTY_NAME_HIBERNATE_C3P0_MIN_SIZE,
//				dbProps.getProperty(PROPERTY_NAME_HIBERNATE_C3P0_MIN_SIZE));
//		properties.put(PROPERTY_NAME_HIBERNATE_C3P0_MAX_SIZE,
//				dbProps.getProperty(PROPERTY_NAME_HIBERNATE_C3P0_MAX_SIZE));
//		properties.put(PROPERTY_NAME_HIBERNATE_C3P0_TIMEOUT, dbProps.getProperty(PROPERTY_NAME_HIBERNATE_C3P0_TIMEOUT));
//		properties.put(PROPERTY_NAME_HIBERNATE_C3P0_MAX_STATEMENTS,
//				dbProps.getProperty(PROPERTY_NAME_HIBERNATE_C3P0_MAX_STATEMENTS));
//		properties.put(PROPERTY_NAME_HIBERNATE_C3P0_IDLE_TEST_PERIOD,
//				dbProps.getProperty(PROPERTY_NAME_HIBERNATE_C3P0_IDLE_TEST_PERIOD));
		return properties;
	}
	
	@Bean
	public DatabaseSessionManager getDatabaseSessionManager() {
		return new DatabaseSessionManager();
	}


}
