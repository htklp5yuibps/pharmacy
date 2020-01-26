package no.comp.finalproject.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import no.comp.finalproject.dao.UserDao;
import no.comp.finalproject.dao.exception.ServiceException;
import no.comp.finalproject.domain.User;
import no.comp.finalproject.domain.constant.UserRole;
import no.comp.finalproject.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao udao;
	
	public void setUserDao(UserDao udao) {
		this.udao = udao;
	}
	
	@Override
	public Long create(final User entity) {
		return this.udao.create(entity);
	}

	@Override
	public List<User> read() {
		return this.udao.read().stream()
				.sorted(userComparator).collect(Collectors.toList());
	}

	@Override
	public Integer update(final User entity) {
		try {
			return this.udao.update(entity);
		} catch (Exception exc) {
			throw new ServiceException(exc);
		}
	}

	@Override
	public User delete(final Long id) {
		return this.udao.delete(id);
	}

	@Override
	public User read(final Long userId) {
		return this.udao.read().stream()
				.filter(user -> user.getId() == userId)
				.findFirst().get();
	}

	@Override
	public User read(final String username) {
		return this.read().stream()
				.filter(user -> username.equals(user.getUsername()))
				.findFirst().get();
	}

	@Override
	public List<User> read(final UserRole role) {
		return this.read().stream()
				.filter(user -> role.toString().toLowerCase().equals(user.getRole()))
				.sorted(userComparator)
				.collect(Collectors.toList());
	}

	@Override
	public List<User> contains(final String part) {
		Set<User> name = this.udao.read().stream()
				.filter(user -> user.getName().toLowerCase().contains(part))
				.collect(Collectors.toSet());
		
		Set<User> surname = this.udao.read().stream()
				.filter(user -> user.getSurname().toLowerCase().contains(part))
				.collect(Collectors.toSet());
		
		Set<User> patronymic = this.udao.read().stream()
				.filter(user -> user.getPatronymic().toLowerCase().contains(part))
				.collect(Collectors.toSet());
		
		Set<User> email = this.udao.read().stream()
				.filter(user -> user.getUsername().toLowerCase().contains(part))
				.collect(Collectors.toSet());
		
		name.addAll(surname);
		name.addAll(patronymic);
		name.addAll(email);
		
		return name.stream().sorted(userComparator).collect(Collectors.toList());
	}
	
	private Comparator<User> userComparator =
			new Comparator<User>() {
		@Override
		public int compare(User o1, User o2) {
			return o1.getId() > o2.getId() ? 1 : -1;
		}
	};
	
}
