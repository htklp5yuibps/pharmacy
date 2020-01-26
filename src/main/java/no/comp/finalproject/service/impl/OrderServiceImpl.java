package no.comp.finalproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import no.comp.finalproject.dao.OrderDao;
import no.comp.finalproject.domain.Order;
import no.comp.finalproject.domain.Product;
import no.comp.finalproject.domain.User;
import no.comp.finalproject.domain.constant.OrderStatus;
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
		return this.read().stream()
				.filter(order -> order.getUserId() == user.getId())
				.collect(Collectors.toList());
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

}
