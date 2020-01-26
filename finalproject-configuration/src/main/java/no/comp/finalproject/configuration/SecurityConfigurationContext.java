package no.comp.finalproject.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@PropertySource("classpath:database.properties")
public class SecurityConfigurationContext extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery(
					"select `username`, `password`, `status` from `user` where `username` = ?")
			.authoritiesByUsernameQuery(
					"select `username`, `role` from `user` where `username` = ?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");

		http.addFilterBefore(encodingFilter, CsrfFilter.class);
		
		http.authorizeRequests()
			.and()
				.formLogin()
					.loginPage("/signin")
					.loginProcessingUrl("/authenticateTheUser")
					.permitAll()
			.and()
				.logout().permitAll();
	}
	
}
