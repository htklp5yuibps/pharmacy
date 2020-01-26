package no.comp.finalproject.service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import no.comp.finalproject.dao.ImageDao;
import no.comp.finalproject.dao.OrderDao;
import no.comp.finalproject.dao.ProductDao;
import no.comp.finalproject.dao.UserDao;
import no.comp.finalproject.service.ImageService;
import no.comp.finalproject.service.OrderService;
import no.comp.finalproject.service.ProductService;
import no.comp.finalproject.service.UserService;
import no.comp.finalproject.service.impl.ImageServiceImpl;
import no.comp.finalproject.service.impl.OrderServiceImpl;
import no.comp.finalproject.service.impl.ProductServiceImpl;
import no.comp.finalproject.service.impl.UserServiceImpl;

@Configuration
public class ServiceConfigurationContext {
	@Bean
	public UserService userService(@Autowired UserDao udao) {
		UserServiceImpl uService = new UserServiceImpl();
		uService.setUserDao(udao);
		return uService;
	}

	@Bean
	public OrderService orderService(@Autowired OrderDao odao) {
		OrderServiceImpl oService = new OrderServiceImpl();
		oService.setOrderDao(odao);
		return oService;
	}

	@Bean
	public ProductService productService(@Autowired ProductDao pdao) {
		ProductServiceImpl pService = new ProductServiceImpl();
		pService.setProductDao(pdao);
		return pService;
	}
	
	@Bean
	public ImageService imageService(@Autowired ImageDao idao) {
		ImageServiceImpl iService = new ImageServiceImpl();
		iService.setImageDao(idao);
		return iService;
	}
}
