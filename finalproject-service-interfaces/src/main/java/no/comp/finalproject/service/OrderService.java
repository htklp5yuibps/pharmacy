package no.comp.finalproject.service;

import java.util.List;

import no.comp.finalproject.entity.Order;
import no.comp.finalproject.entity.Product;
import no.comp.finalproject.entity.User;
import no.comp.finalproject.entity.constant.OrderStatus;

public interface OrderService extends BaseService<Order> {
	
	List<Order> read(OrderStatus status);
	List<Order> read(Product product);
	List<Order> read(OrderStatus status, Product product);
	List<Order> read(User user);
	List<Order> read(User user, OrderStatus status);
	List<Order> read(User user, Product product);
	List<Order> read(User user, OrderStatus status, Product product);
	List<Order> read(List<User> users);
	Order read(Long id);
	boolean close(Order order);
	boolean ready(Order order);
	boolean cancel(Order order);
}
