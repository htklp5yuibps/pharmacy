package no.comp.finalproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import no.comp.finalproject.dao.UserDao;
import no.comp.finalproject.entity.User;

@Repository
@Transactional
public class HibernateUserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Long create(User user) {
		entityManager.persist(user);
		return user.getId();
	}

	@Override
	public List<User> read() {
		List<User> users =
				entityManager.createQuery("from user", User.class)
				.getResultList();
		
		return users;
	}

	@Override
	public Integer update(User user) {
		entityManager.merge(user);
		return 1;
	}

	@Override
	public User delete(Long id) {
		User user = entityManager.find(User.class, id);
		user.setStatus(0);
		entityManager.merge(user);
		entityManager.close();
		return user;
	}

}
