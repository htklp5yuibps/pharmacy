package no.comp.finalproject.dao.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "no.comp.finalproject.dao.impl" })
@PropertySource(value = { "classpath:database.properties" })
public class HibernateDaoConfigurationContext {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManager =
				new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setDataSource(dataSource);
		entityManager.setPackagesToScan("no.comp.finalproject.entity");
		entityManager.setJpaVendorAdapter(vendorAdapter);
		entityManager.setJpaProperties(hibernateProperties());
		return entityManager;
	}

	@Bean
	public PlatformTransactionManager txManager(@Autowired EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	    return new PersistenceExceptionTranslationPostProcessor();
	}
	
	private Properties hibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.sql_dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.current_session_context_class", "thread");
		// props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		return props;
	}
	
}
