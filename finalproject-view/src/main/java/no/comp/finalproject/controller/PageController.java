package no.comp.finalproject.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import no.comp.finalproject.entity.Order;
import no.comp.finalproject.entity.Product;
import no.comp.finalproject.entity.User;
import no.comp.finalproject.entity.constant.OrderStatus;
import no.comp.finalproject.entity.constant.ProductStatus;
import no.comp.finalproject.entity.constant.ProductType;
import no.comp.finalproject.entity.constant.UserRole;
import no.comp.finalproject.entity.constant.UserStatus;
import no.comp.finalproject.service.OrderService;
import no.comp.finalproject.service.ProductService;
import no.comp.finalproject.service.UserService;
import no.comp.finalproject.util.Cart;

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
		List<Product> products = pService.read(null, part);
		
		System.out.println(">>>>>>>>>>>>>PageController.indexPage()>>>>>>>>>>>>");
		System.out.println(products.size());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		model.addObject("products", products);
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
	public ModelAndView admin_productEdition(
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "name", required = false) String name) {
		
		if (status != null && !"".equals(status)) {
			ModelAndView model = new ModelAndView("productEditionPage");
			model.addObject("products", pService.read(ProductStatus.valueOf(status.toUpperCase())));
			return model;
		}
		
		if (name != null && !"".equals(name)) {
			ModelAndView model = new ModelAndView("productEditionPage");
			model.addObject("products", pService.read(name));
			return model;
		}
		
		ModelAndView model = new ModelAndView("productEditionPage");
		model.addObject("products", pService.read());
		return model;
	}
	
	@RequestMapping(value = "/admin/product/add/form")
	public ModelAndView admin_product_addForm() {
		ModelAndView model = new ModelAndView("admin/product/add");
		model.addObject("types", ProductType.values());
		return model;
	}
	
	@RequestMapping(value = "/admin/product/editor")
	public ModelAndView admin_product_editor(
			@RequestParam(value = "productId") Long productId) {
		
		ModelAndView model = new ModelAndView("productEditorPage");
		Product product = pService.read(productId);
		model.addObject("product", product);
		return model;
	}
	
	@RequestMapping(value = "/employee/orderEditionPage")
	public ModelAndView employee_orderEdition(
			@RequestParam(value = "show", required = false) String showOnly,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "strict", required = false) Boolean strict) {
		
		ModelAndView model = new ModelAndView("orderEditionPage");
		
		if (username == null || "".equals(username)) {
			if (showOnly == null) {
				model.addObject("orders", oService.read());
				return model;
			} else {
				model.addObject("orders",
						oService.read(OrderStatus.valueOf(showOnly.toUpperCase())));
			}			
		} else {
			if (strict != null) {
				User user = uService.read(username);
				model.addObject("orders", oService.read(user));		
			} else {
				model.addObject("orders", oService.read(uService.contains(username)));
			}
		}
		
		return model;
	}
	
	@RequestMapping(value = "/employee/order/viewOrder")
	public ModelAndView employee_order_viewOrder(
			@RequestParam(value = "id") Long orderId) {
		ModelAndView model = new ModelAndView("viewOrder");
		Order order = oService.read(orderId);
		List<Map.Entry<Product, Integer>> productsWithCount = new ArrayList<>();
		
		// find all products from order parts list
		for (Map.Entry<Long, Integer> part: order.getParts().entrySet()) {
			productsWithCount
				.add(new AbstractMap.SimpleEntry<Product, Integer>(
						pService.read(part.getKey()), part.getValue()));
		}
		
		productsWithCount.sort(new Comparator<Map.Entry<Product, Integer>>() {
			@Override
			public int compare(Entry<Product, Integer> o1, Entry<Product, Integer> o2) {
				return (int)(o1.getKey().getId() - o2.getKey().getId());
			}
		});
		
		model.addObject("owner", uService.read(order.getUserId()));
		model.addObject("order", oService.read(orderId));
		model.addObject("products", productsWithCount);
		return model;
	}
	
	@RequestMapping(value = "/admin/accessLevelEditionPage")
	public ModelAndView admin_accessLevelEdition(
			@RequestParam(value = "role", required = false) String role,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "name", required = false) String name) {
		ModelAndView model = new ModelAndView("accessLevelEditionPage");
		UserRole r = null;
		UserStatus s = null;
		
		if (role != null && !"".equals(role)) {
			r = UserRole.valueOf(role.toUpperCase());
		}
		
		if (status != null && !"".equals(status)) {
			s = UserStatus.valueOf(status.toUpperCase());
		}
		
		model.addObject("users", uService.like(name, r, s));
		return model;
	}
	
	@RequestMapping(value = "/user/personalPage")
	public ModelAndView user_personalPage(
			Authentication auth) {
		User user = null;
		if (auth != null) {
			user = uService.read(auth.getName());
		}
		ModelAndView model = new ModelAndView("personalPage");
		model.addObject("user", user);
		model.addObject("orders", oService.read(user));
		return model;
	}
	
	@RequestMapping(value = "/user/personalCartPage")
	public ModelAndView user_personalCartPage(HttpSession session) {
		ModelAndView model = new ModelAndView("personalCartPage");
		Cart cart = (Cart)session.getAttribute("cart");
		List<Product> products = new ArrayList<>();
		
		if (cart != null) {
			for (Map.Entry<Long, Integer> part: cart.getParts().entrySet()) {
				products.add(pService.read(part.getKey()));
			}
			model.addObject("products", products);
			return model;			
		} else {
			model.addObject("products", products);
			return model;
		}
		
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
	
	@RequestMapping(value = "/user/order/view")
	public ModelAndView user_order_view(
			@RequestParam(value = "orderId") Long orderId,
			Authentication auth) {
		
		if (auth != null) {
			ModelAndView model = new ModelAndView("user/viewOrder");
			User user = uService.read(auth.getName());
			if (user != null) {
				Order order = oService.read(orderId);
				if (order.getUserId() == user.getId()) {
					List<Map.Entry<Product, Integer>> productsWithCount = new ArrayList<>();
					for (Map.Entry<Long, Integer> part: order.getParts().entrySet()) {
						productsWithCount
							.add(new AbstractMap.SimpleEntry<Product, Integer>(
									pService.read(part.getKey()), part.getValue()
									));
					}
					
					productsWithCount.sort(new Comparator<Map.Entry<Product, Integer>>() {
						@Override
						public int compare(Entry<Product, Integer> o1, Entry<Product, Integer> o2) {
							return (int)(o1.getKey().getId() - o2.getKey().getId());
						}
					});
					model.addObject("order", order);
					model.addObject("products", productsWithCount);
					return model;
				}
			}
		}
		ModelAndView model = new ModelAndView("user/viewOrder");
		return model;
	}
	
	@RequestMapping(value = "/signin")
	public String signin() {
		return "signin";
	}
	
	@RequestMapping(value = "/registration")
	public String registration() {
		return "registrationPage";
	}
	
}
