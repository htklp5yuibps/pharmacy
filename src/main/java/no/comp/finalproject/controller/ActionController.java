package no.comp.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import no.comp.finalproject.domain.User;
import no.comp.finalproject.service.OrderService;
import no.comp.finalproject.service.ProductService;
import no.comp.finalproject.service.UserService;

@Controller
public class ActionController {

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
	
	public UserService getUserService() {
		return this.uService;
	}
	
	public OrderService getOrderService() {
		return this.oService;
	}
	
	public ProductService getProductService() {
		return this.pService;
	}
	
	
	@RequestMapping(value = "/user/updatePersonalData", method = RequestMethod.POST)
	public String user_updatePersonalData(
			@RequestParam("name") String name,
			@RequestParam("surname") String surname,
			@RequestParam("patronymic") String patronymic,
			Authentication auth) {
		
		if (auth != null) {
			User user = uService.read(auth.getName());			
			user.setName(name);
			user.setSurname(surname);
			user.setPatronymic(patronymic);
			uService.update(user);
			return "redirect:/user/personalPage";
		} 
		
		return "redirect:/user/personalPage?error";
		
	}
	
	@RequestMapping("/user/updatePassword")
	public String user_updatePassword(
			@RequestParam("oldPass") String oldPassword,
			@RequestParam("newPass") String newPassword,
			Authentication auth) {
		if (auth != null) {
			User user = uService.read(auth.getName());
			if (BCrypt.checkpw(oldPassword, user.getPassword().replace("{bcrypt}", ""))) {
				if (newPassword != null & !"".equals(newPassword)) {
					String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
					user.setPassword(hashedNewPassword);
					uService.update(user);
					return "redirect:/logout";
				}
			}
		}
		
		return "redirect:/user/passwordChangingPage?error";
	}
	
}
