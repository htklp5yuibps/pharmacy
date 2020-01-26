package no.comp.finalproject.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCrypt;

import no.comp.finalproject.dao.UserDao;
import no.comp.finalproject.dao.exception.DaoException;
import no.comp.finalproject.entity.User;
import no.comp.finalproject.entity.constant.UserRole;
import no.comp.finalproject.entity.constant.UserStatus;
import no.comp.finalproject.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao udao;

	public void setUserDao(UserDao udao) {
		this.udao = udao;
	}

	@Override
	public Long create(final User entity) {
		String password = entity.getPassword();
		password = BCrypt.hashpw(password, BCrypt.gensalt());
		password = "{bcrypt}" + password;
		entity.setPassword(password);
		return this.udao.create(entity);
	}

	@Override
	public List<User> read() {
		return this.udao.read().stream().sorted(userComparator).collect(Collectors.toList());
	}

	@Override
	public Integer update(final User entity) {
		try {
			return this.udao.update(entity);
		} catch (Exception exc) {
			throw new DaoException(exc);
		}
	}

	@Override
	public User delete(final Long id) {
		return this.udao.delete(id);
	}

	@Override
	public User read(final Long userId) {
		return this.udao.read().stream().filter(user -> user.getId() == userId).findFirst().get();
	}

	@Override
	public User read(final String username) {
		return this.read().stream().filter(user -> username.equals(user.getUsername())).findFirst().orElse(null);
	}

	@Override
	public List<User> read(final UserRole role) {
		return this.read().stream().filter(user -> role.toString().toLowerCase().equals(user.getRole()))
				.sorted(userComparator).collect(Collectors.toList());
	}

	@Override
	public List<User> contains(final String part) {
		Set<User> name = this.udao.read().stream().filter(user -> user.getName().toLowerCase().contains(part.toLowerCase()))
				.collect(Collectors.toSet());

		Set<User> surname = this.udao.read().stream()
				.filter(user -> user.getSurname().toLowerCase().contains(part.toLowerCase())).collect(Collectors.toSet());

		Set<User> patronymic = this.udao.read().stream()
				.filter(user -> user.getPatronymic().toLowerCase().contains(part.toLowerCase())).collect(Collectors.toSet());

		Set<User> email = this.udao.read().stream()
				.filter(user -> user.getUsername().toLowerCase().contains(part.toLowerCase())).collect(Collectors.toSet());

		name.addAll(surname);
		name.addAll(patronymic);
		name.addAll(email);

		return name.stream().sorted(userComparator).collect(Collectors.toList());
	}

	private Comparator<User> userComparator = new Comparator<User>() {
		@Override
		public int compare(User o1, User o2) {
			return o1.getId() > o2.getId() ? 1 : -1;
		}
	};

	@Override
	public List<User> like(String username, UserRole role, UserStatus status) {
		List<User> users = udao.read();
		if (username != null && !"".equals(username)) {
			users = this.contains(username);
		}
		if (role != null) {
			users = users.stream().filter(user -> role.toString().toLowerCase().equals(user.getRole()))
					.collect(Collectors.toList());
		}
		if (status != null) {
			users = users.stream().filter(user -> status.ordinal() == user.getStatus()).collect(Collectors.toList());
		}
		
		return users;
	}
}
