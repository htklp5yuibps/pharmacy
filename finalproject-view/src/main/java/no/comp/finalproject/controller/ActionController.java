package no.comp.finalproject.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.x.protobuf.MysqlxNotice.SessionStateChanged.Parameter;

import no.comp.finalproject.entity.Image;
import no.comp.finalproject.entity.Order;
import no.comp.finalproject.entity.Product;
import no.comp.finalproject.entity.User;
import no.comp.finalproject.entity.constant.OrderStatus;
import no.comp.finalproject.entity.constant.ProductStatus;
import no.comp.finalproject.entity.constant.ProductType;
import no.comp.finalproject.entity.constant.UserRole;
import no.comp.finalproject.entity.constant.UserStatus;
import no.comp.finalproject.service.ImageService;
import no.comp.finalproject.service.OrderService;
import no.comp.finalproject.service.ProductService;
import no.comp.finalproject.service.UserService;
import no.comp.finalproject.util.Cart;

@Controller
public class ActionController {

	private UserService uService;
	private OrderService oService;
	private ProductService pService;
	private ImageService iService;

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
	
	@Autowired
	public void setImageService(ImageService iService) {
		this.iService = iService;
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
	public String user_updatePersonalData(@RequestParam("name") String name, @RequestParam("surname") String surname,
			@RequestParam("patronymic") String patronymic, Authentication auth) {

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

	@RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
	public String user_updatePassword(@RequestParam("oldPass") String oldPassword,
			@RequestParam("newPass") String newPassword, Authentication auth, HttpServletRequest response)
			throws ServletException {
		if (auth != null) {
			User user = uService.read(auth.getName());
			if (BCrypt.checkpw(oldPassword, user.getPassword())) {
				if (newPassword != null & !"".equals(newPassword)) {
					String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
					user.setPassword(hashedNewPassword);
					uService.update(user);
					response.logout();
					return "redirect:/signin";
				}
			}
		}

		return "redirect:/user/passwordChangingPage?error";
	}

	@RequestMapping(value = "/employee/order/closeOrder")
	public String employee_order_closeOrder(@RequestParam(value = "id") Long orderId, HttpServletRequest request) {

		Order order = oService.read(orderId);
		oService.close(order);

		return "redirect:" + request.getAttribute("referer");
	}

	@RequestMapping(value = "/employee/order/readyOrder")
	public String employee_order_readyOrder(@RequestParam(value = "id") Long orderId, HttpServletRequest request) {

		Order order = oService.read(orderId);
		oService.ready(order);

		return "redirect:" + request.getAttribute("referer");
	}

	@RequestMapping(value = "/employee/order/cancelOrder")
	public String employee_order_cancelOrder(@RequestParam(value = "id") Long orderId, HttpServletRequest request) {

		Order order = oService.read(orderId);
		oService.cancel(order);

		return "redirect:" + request.getAttribute("referer");
	}

	@RequestMapping(value = "/admin/product/delete")
	public String admin_product_delete(@RequestParam(value = "productId") Long productId, HttpServletRequest request) {

		Product product = pService.read(productId);
		product.setStatus(ProductStatus.UNAVAILABLE.toString().toLowerCase());
		pService.update(product);
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/admin/product/restore")
	public String admin_product_restore(@RequestParam(value = "productId") Long productId, HttpServletRequest request) {

		Product product = pService.read(productId);
		product.setStatus(ProductStatus.AVAILABLE.toString().toLowerCase());
		pService.update(product);
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/admin/product/update", method = RequestMethod.POST)
	public String admin_product_update(@RequestParam(value = "productId") Long productId,
			@RequestParam(value = "name") String name, @RequestParam(value = "info") String info,
			@RequestParam(value = "cost") String cost, HttpServletRequest request, RedirectAttributes attrs) {

		String referer = request.getHeader("Referer").replace("&error", "").replace("&success", "");

		try {
			Product product = pService.read(productId);
			product.setName(name);
			product.setInfo(info);
			product.setCost(Double.parseDouble(cost.replace(",", ".")));
			pService.update(product);
		} catch (Exception exc) {
			return "redirect:" + referer + "&error";
		}
		return "redirect:" + referer + "&success";
	}

	@RequestMapping(value = "/admin/product/add")
	public String admin_product_add(@RequestParam(value = "name") String name, @RequestParam(value = "info") String info,
			@RequestParam(value = "cost") String cost, @RequestParam(value = "type") Integer type) {

		try {
			Product product = new Product();
			product.setName(name);
			product.setInfo(info);
			product.setCost(Double.parseDouble(cost.replace(",", ".")));
			product.setType(ProductType.values()[type].toString().toLowerCase());
			pService.create(product);
			return "redirect:/admin/product/add/form?success";
		} catch (Exception exc) {
			return "redirect:/admin/product/add/form?error";
		}
	}
	
	@RequestMapping(value = "/admin/image/product_image/delete")
	public String admin_images_productImage_delete(
			@RequestParam(value = "imageId") Long imageId,
			@RequestParam(value = "productId") Long productId,
			HttpServletRequest request) {
		
		Product product = pService.read(productId);
		Long[] images = product.getImages();
		
		List<Long> imagesList = new ArrayList<Long>();
		
		for(Long _imageId: images) {
			if (_imageId != imageId) {
				imagesList.add(_imageId);				
			}
		}
	
		product.setImages(imagesList.toArray(new Long[imagesList.size()]));
		pService.update(product);
		
		return "redirect:" + request.getAttribute("referer");
	}
	
	@RequestMapping(value = "/admin/image/count")
	@ResponseBody
	public String admin_image_count(
			@RequestParam(value = "type", required = false) String imageType) {
		
		List<Image> images = iService.read();
		List<User> users = uService.read();
		List<Product> products = pService.read();
		
		if (imageType != null) {
			switch (imageType) {
				case "productImage": {
					Integer count = 0;
					for(Product _product: products) {
						count += _product.getImages().length;
					}
					return "" + count;
				}
				case "avatar": {
					Integer count = 0;
					for (User _user: users) {
						count += _user.getImageId() == null ? 0 : _user.getImageId() >= 1 ? 1 : 0;
					}
					return "" + count;
				}
				default: {
					return "" + images.size();
				}
			}
		}
		
		return "" + iService.read().size();
		
	}

	@RequestMapping(value = "/addProductToCart", method = RequestMethod.POST)
	public String addProductToCart(@RequestParam(value = "productId") Long productId,
			@RequestParam(value = "count") Integer count, HttpSession session) {

		if (count == null || count <= 0) {
			return "redirect:/product/viewProductPage?productId=" + productId + "&error";
		} else {
			Product product = pService.read(productId);
			if (product == null) {
				return "redirect:/product/viewProductPage";
			} else {
				Cart cart = (Cart) session.getAttribute("cart");
				if (cart == null) {
					cart = new Cart();
				}
				cart.addProduct(productId, count);
				session.setAttribute("cart", cart);
				return "redirect:/product/viewProductPage?productId=" + productId + "&success";
			}
		}

	}

	@RequestMapping(value = "/cart/deleteFromCart")
	public String cart_deleteFromCart(@RequestParam(value = "productId") Long productId, HttpSession session) {

		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			cart.removeFromCart(productId);
			session.setAttribute("cart", cart);
		} else {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		return "redirect:/user/personalCartPage";
	}

	@RequestMapping(value = "/cart/clearCart")
	public String cart_clearCart(HttpSession session) {

		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			cart.clearCart();
		}
		return "redirect:/user/personalCartPage?cleared";
	}

	@RequestMapping(value = "/cart/createOrder")
	public String cart_createOrder(HttpSession session, Authentication auth) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null && cart.getPartsCount() > 0 & auth != null) {
			User user = uService.read(auth.getName());
			Order order = new Order();
			order.setUserId(user.getId());
			order.setParts(cart.getParts());
			oService.create(order);
			cart.clearCart();
			return "redirect:/user/personalPage?created";
		}
		return "redirect: /user/personalPage?error";
	}

	@RequestMapping(value = "/admin/products/count")
	public void admin_products_count_all(@RequestParam(value = "type", required = false) String type,
			HttpServletResponse response) throws IOException {
		if (type != null) {
			if (ProductStatus.AVAILABLE.toString().toLowerCase().equals(type)) {
				OutputStream os = response.getOutputStream();
				os.write(String.valueOf(pService.read(ProductStatus.AVAILABLE).size()).getBytes());
			} else if (ProductStatus.UNAVAILABLE.toString().toLowerCase().equals(type)) {
				OutputStream os = response.getOutputStream();
				os.write(String.valueOf(pService.read(ProductStatus.UNAVAILABLE).size()).getBytes());
			}
		} else {
			OutputStream os = response.getOutputStream();
			os.write(String.valueOf(pService.read().size()).getBytes());
		}
	}

	@RequestMapping(value = "/admin/users/count")
	public void admin_users_count(@RequestParam(value = "role", required = false) String role,
			HttpServletResponse response) throws IOException {

		if (role != null) {
			if (UserRole.ADMIN.toString().toLowerCase().equals(role)) {
				OutputStream os = response.getOutputStream();
				os.write(String.valueOf(uService.read(UserRole.ADMIN).size()).getBytes());
			} else if (UserRole.EMPLOYEE.toString().toLowerCase().equals(role)) {
				OutputStream os = response.getOutputStream();
				os.write(String.valueOf(uService.read(UserRole.EMPLOYEE).size()).getBytes());
			} else if (UserRole.USER.toString().toLowerCase().equals(role)) {
				OutputStream os = response.getOutputStream();
				os.write(String.valueOf(uService.read(UserRole.USER).size()).getBytes());
			}
		} else {
			OutputStream os = response.getOutputStream();
			os.write(String.valueOf(uService.read().size()).getBytes());
		}
	}

	@RequestMapping(value = "/admin/orders/count")
	@ResponseBody
	public String admin_orders_count(@RequestParam(value = "status", required = false) String status) {
		if (status != null) {
			if (OrderStatus.NEW.toString().toLowerCase().equals(status)) {
				return String.valueOf(oService.read(OrderStatus.NEW).size());
			} else if (OrderStatus.CLOSED.toString().toLowerCase().equals(status)) {
				return String.valueOf(oService.read(OrderStatus.CLOSED).size());
			} else if (OrderStatus.CANCELED.toString().toLowerCase().equals(status)) {
				return String.valueOf(oService.read(OrderStatus.CANCELED).size());
			} else if (OrderStatus.READY.toString().toLowerCase().equals(status)) {
				return String.valueOf(oService.read(OrderStatus.READY).size());
			} else {
				return String.valueOf(oService.read().size());
			}
		} else {
			return String.valueOf(oService.read().size());
		}
	}

	@RequestMapping(value = "/admin/users/username")
	@ResponseBody
	public String admin_users_username(@RequestParam(value = "userId") Long userId) {

		User user = uService.read(userId);

		if (user != null) {
			return user.getUsername();
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/admin/users/updateRole")
	public String admin_user_updateRole(@RequestParam(value = "userId") Long userId,
			@RequestParam(value = "role") String role, HttpServletRequest request) {

		String referer = (String)request.getAttribute("referer");

		User user = uService.read(userId);
		if (user != null) {
			if (role != null) {
				user.setRole(UserRole.valueOf(role.toUpperCase()).toString().toLowerCase());
				uService.update(user);

				if (referer.contains("?")) {
					return "redirect:" + referer + "&role_changed";					
				} else {
					return "redirect:" + referer + "?role_changed";
				}
			}
		}
		
		if (referer.contains("?")) {
			return "redirect:" + request.getAttribute("referer") + "&error";					
		} else {
			return "redirect:" + request.getAttribute("referer") + "?error";
		}
	}

	@RequestMapping(value = "/admin/users/delete")
	public String admin_user_delete(@RequestParam(value = "userId") Long userId, HttpServletRequest request) {
		try {
			User user = uService.read(userId);
			user.setStatus(UserStatus.INACTIVE.ordinal());
			uService.update(user);
			return "redirect:" + request.getAttribute("referer") + "?deleted";
		} catch (Exception exc) {
			return "redirect:" + request.getAttribute("referer") + "?error";
		}
	}

	@RequestMapping(value = "/admin/users/restore")
	public String admin_user_restore(@RequestParam(value = "userId") Long userId, HttpServletRequest request) {
		try {
			User user = uService.read(userId);
			user.setStatus(UserStatus.ACTIVE.ordinal());
			uService.update(user);
			return "redirect:" + request.getAttribute("referer") + "?restored";
		} catch (Exception exc) {
			return "redirect:" + request.getAttribute("referer") + "?error";
		}
	}
	
	@RequestMapping(value = "/registration/signup")
	public String registration_signup(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "surname") String surname,
			@RequestParam(value = "patronymic") String patronymic) {
		
		try {
			User user = uService.read(username);
			if (user == null) {
				user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setName(name);
				user.setSurname(surname);
				user.setPatronymic(patronymic);
				
				uService.create(user);
				
				return "redirect:/signin?registered";
			} else {
				return "redirect:/registration?exists";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/registration?error";
		}
	}
	
	@RequestMapping(value = "/images/getimage")
	public void images_getimage(
			@RequestParam(value = "imageId") Long imageId,
			HttpServletResponse response)
					throws IOException, SQLException  {
		response.getOutputStream()
			.write(iService.read(imageId).getImage().getBinaryStream().readAllBytes());
	}

}
