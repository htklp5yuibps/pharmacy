package no.comp.finalproject.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import no.comp.finalproject.dao.OrderDao;
import no.comp.finalproject.entity.Order;
import no.comp.finalproject.entity.constant.OrderStatus;

@Repository
@Transactional
public class HibernateOrderDaoImpl implements OrderDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Long create(Order entity) {
		entityManager.persist(entity);
		return entity.getId();
	}

	@Override
	public List<Order> read() {
		List<Order> orders = entityManager.createQuery("from order", Order.class).getResultList();
		return orders;
	}

	@Override
	public Integer update(Order entity) {
		entityManager.merge(entity);
		return 1;
	}

	@Override
	public Order delete(Long id) {
		Order order = entityManager.find(Order.class, id);
		order.setStatus(OrderStatus.CANCELED.toString().toLowerCase());
		order.setEndDate(new Date());
		entityManager.merge(order);
		return order;
	}

}
