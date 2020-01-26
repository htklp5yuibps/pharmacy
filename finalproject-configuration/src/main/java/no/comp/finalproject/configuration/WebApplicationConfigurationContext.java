package no.comp.finalproject.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import no.comp.finalproject.dao.configuration.HibernateDaoConfigurationContext;
import no.comp.finalproject.interceptor.ViewMessageCleanerInterceptor;
import no.comp.finalproject.service.configuration.ServiceConfigurationContext;

@Configuration
@PropertySource("classpath:database.properties")
@EnableWebMvc
@ComponentScan(value = {
		"no.comp.finalproject.controller"
})
@Import(value = {
		ServiceConfigurationContext.class,
		SecurityConfigurationContext.class,
		HibernateDaoConfigurationContext.class,
		DataBaseInitConfigurationContext.class
})
public class WebApplicationConfigurationContext implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		return dataSource;
	}
	
	@Bean
	public ViewResolver resolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		resolver.setCache(false);
		resolver.setContentType("text/html; charset=UTF-8");
		return resolver;
	}

	@Bean
	public FreeMarkerConfig freeMarkerConfig() {
		FreeMarkerConfigurer configuration = new FreeMarkerConfigurer();
		configuration.setTemplateLoaderPath("/WEB-INF/ftl/");
		configuration.setDefaultEncoding("UTF-8");
		return configuration;
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(
				"/css/**",
				"/images/**",
				"/images/default/**",
				"/bootstrap/**",
				"/js/**", 
				"/fonts/**")
			.addResourceLocations(
					"/resources/css/",
					"/resources/images/",
					"/resources/bootstrap/",
					"/resources/js/",
					"/resources/fonts/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ViewMessageCleanerInterceptor());
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
