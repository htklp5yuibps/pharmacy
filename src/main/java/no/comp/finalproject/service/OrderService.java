package no.comp.finalproject.service;

import java.util.List;

import no.comp.finalproject.domain.Order;
import no.comp.finalproject.domain.Product;
import no.comp.finalproject.domain.User;
import no.comp.finalproject.domain.constant.OrderStatus;

public interface OrderService extends BaseService<Order> {
	
	List<Order> read(OrderStatus status);
	List<Order> read(Product product);
	List<Order> read(OrderStatus status, Product product);
	List<Order> read(User user);
	List<Order> read(User user, OrderStatus status);
	List<Order> read(User user, Product product);
	List<Order> read(User user, OrderStatus status, Product product);
	Order read(Long id);
	
}
