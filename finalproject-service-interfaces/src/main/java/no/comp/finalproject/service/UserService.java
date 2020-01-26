package no.comp.finalproject.service;

import java.util.List;

import no.comp.finalproject.entity.User;
import no.comp.finalproject.entity.constant.UserRole;
import no.comp.finalproject.entity.constant.UserStatus;

public interface UserService extends BaseService<User> {

	User read(Long userId);
	User read(String username);
	List<User> read(UserRole role);
	List<User> contains(String part);
	List<User> like(String username, UserRole role, UserStatus status);
}
