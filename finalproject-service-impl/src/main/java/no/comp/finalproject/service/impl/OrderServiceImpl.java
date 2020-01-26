package no.comp.finalproject.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import no.comp.finalproject.dao.OrderDao;
import no.comp.finalproject.entity.Order;
import no.comp.finalproject.entity.Product;
import no.comp.finalproject.entity.User;
import no.comp.finalproject.entity.constant.OrderStatus;
import no.comp.finalproject.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderDao odao;
	
	public void setOrderDao(OrderDao odao) {
		this.odao = odao;
	}
	
	@Override
	public Long create(Order entity) {
		return odao.create(entity);
	}

	@Override
	public List<Order> read() {
		return odao.read();
	}

	@Override
	public Integer update(Order entity) {
		return this.odao.update(entity);
	}

	@Override
	public Order delete(Long id) {
		return this.odao.delete(id);
	}

	@Override
	public List<Order> read(OrderStatus status) {
		return this.odao.read().stream()
				.filter(order -> order.getStatus().equals(status.toString().toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Order> read(Product product) {
		return this.odao.read().stream()
				.filter(order -> order.getParts().containsKey(product.getId()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Order> read(OrderStatus status, Product product) {
		return this.odao.read().stream()
				.filter(order -> order.getStatus().equals(status.toString().toLowerCase()))
				.filter(order -> order.getParts().containsKey(product.getId()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Order> read(User user) {
		if (user != null) {
			return this.read().stream()
					.filter(order -> order.getUserId() == user.getId())
					.collect(Collectors.toList());			
		}
		
		return new ArrayList<Order>();
	}

	@Override
	public List<Order> read(User user, OrderStatus status) {
		return this.odao.read().stream()
				.filter(order -> order.getUserId() == user.getId())
				.filter(order -> order.getStatus().equals(status.toString().toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Order> read(User user, Product product) {
		return this.odao.read().stream()
				.filter(order -> order.getUserId() == user.getId())
				.filter(order -> order.getParts().containsKey(product.getId()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Order> read(User user, OrderStatus status, Product product) {
		return this.read().stream()
				.filter(order -> order.getUserId() == user.getId())
				.filter(order -> order.getStatus().equals(status.toString().toLowerCase()))
				.filter(order -> order.getParts().containsKey(product.getId()))
				.collect(Collectors.toList());
	}

	@Override
	public Order read(Long id) {
		return this.odao.read().stream()
				.filter(order -> order.getId() == id)
				.findFirst().get();
	}

	@Override
	public boolean close(Order order) {
		order.setEndDate(new Date());
		order.setStatus("closed");
		odao.update(order);
		return true;
	}
	
	@Override
	public boolean ready(Order order) {
		order.setStatus(OrderStatus.READY.toString().toLowerCase());
		odao.update(order);
		return true;
	}
	
	@Override
	public boolean cancel(Order order) {
		order.setEndDate(new Date());
		order.setStatus(OrderStatus.CANCELED.toString().toLowerCase());
		odao.update(order);
		return true;
	}

	@Override
	public List<Order> read(List<User> users) {
		List<Order> orders = odao.read();
		Set<Order> ordersSet = new HashSet<>();
		
		for (User user: users) {
			ordersSet.addAll(orders.stream().filter(order -> order.getUserId() == user.getId())
				.collect(Collectors.toList()));
		}
		
		return ordersSet.stream().collect(Collectors.toList());
	}

}
