package no.comp.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import no.comp.finalproject.domain.User;
import no.comp.finalproject.domain.constant.OrderStatus;
import no.comp.finalproject.domain.constant.ProductStatus;
import no.comp.finalproject.domain.constant.UserRole;
import no.comp.finalproject.service.OrderService;
import no.comp.finalproject.service.ProductService;
import no.comp.finalproject.service.UserService;

@Controller
public class PageController {

	private UserService uService;
	private OrderService oService;
	private ProductService pService;
	
	@Autowired
	public void setUserService(UserService uService) {
		this.uService = uService;
	}
	
	@Autowired
	public void setOrderService(OrderService oService) {
		this.oService = oService;
	}
	
	@Autowired
	public void setProductService(ProductService pService) {
		this.pService = pService;
	}
	
	@RequestMapping(value = { "/", "/index" })
	public ModelAndView indexPage(
			@RequestParam(value = "part", required = false) String part,
			@RequestParam(value = "type", required = false) String type) {
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("products", pService.read(null, part));
		return model;
	}
	
	@RequestMapping(value = "/product/viewProductPage")
	public ModelAndView product_viewProduct(
			@RequestParam(value = "productId") Long prodId) {
		ModelAndView model = new ModelAndView("viewProduct");
		model.addObject("product", pService.read(prodId));
		return model;
	}
	
	@RequestMapping(value = "/admin/productEditionPage")
	public ModelAndView admin_productEdition() {
		ModelAndView model = new ModelAndView("productEditionPage");
		model.addObject("products", pService.read());
		return model;
	}
	
	@RequestMapping(value = "/employee/orderEditionPage")
	public ModelAndView employee_orderEdition(
			@RequestParam(value = "show", required = false) String showOnly) {
		
		ModelAndView model = new ModelAndView("orderEditionPage");
		
		if (showOnly == null) {
			model.addObject("orders", oService.read());
			return model;
		} else {
			model.addObject("orders",
					oService.read(OrderStatus.valueOf(showOnly.toUpperCase())));
		}
		
		return model;
	}
	
	@RequestMapping(value = "/admin/accessLevelEditionPage")
	public ModelAndView admin_accessLevelEdition(
			@RequestParam(value = "show", required = false) String show) {
		ModelAndView model = new ModelAndView("accessLevelEditionPage");
		
		if (show != null) {
			model.addObject("users", uService.read(UserRole.valueOf(show.toUpperCase())));
		} else {
			model.addObject("users", uService.read());			
		}
		
		return model;
	}
	
	@RequestMapping(value = "/user/personalPage")
	public ModelAndView user_personalPage() {
		ModelAndView model = new ModelAndView("personalPage");
		User user = uService.read("admin01@mail.ru");
		model.addObject("user", user);
		model.addObject("orders", oService.read(user));
		return model;
	}
	
	@RequestMapping(value = "/user/personalDataEditionPage")
	public ModelAndView user_personalDataEditionPage() {
		ModelAndView model = new ModelAndView("personalDataEditionPage");
		User user = uService.read("admin01@mail.ru");
		model.addObject("user", user);
		return model;
	}
	
	@RequestMapping(value = "/user/passwordChangingPage")
	public ModelAndView user_passwordChangingPage(
			@RequestParam(value = "error", required = false) String error) {
		ModelAndView model = new ModelAndView("passwordChangingPage");
		return model;
	}
	
	@RequestMapping(value = "/signin")
	public String signin() {
		return "signin";
	}
	
}
