package no.comp.finalproject.dao.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import no.comp.finalproject.dao.OrderDao;
import no.comp.finalproject.dao.ProductDao;
import no.comp.finalproject.dao.UserDao;
import no.comp.finalproject.dao.impl.MysqlOrderDaoImpl;
import no.comp.finalproject.dao.impl.MysqlProductDaoImpl;
import no.comp.finalproject.dao.impl.MysqlUserDaoImpl;

@Configuration
public class JdbcDaoConfigurationContext {
	
	
	@Bean
	public UserDao userDao(@Autowired JdbcTemplate template) {
		MysqlUserDaoImpl udao = new MysqlUserDaoImpl();
		udao.setJdbcTemplate(template);
		return udao;
	}

	@Bean
	public OrderDao orderDao(@Autowired JdbcTemplate template) {
		MysqlOrderDaoImpl odao = new MysqlOrderDaoImpl();
		odao.setJdbcTemplate(template);
		return odao;
	}

	@Bean
	public ProductDao productDao(@Autowired JdbcTemplate template) {
		MysqlProductDaoImpl pdao = new MysqlProductDaoImpl();
		pdao.setJdbcTemplate(template);
		return pdao;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		return template;
	}
}
