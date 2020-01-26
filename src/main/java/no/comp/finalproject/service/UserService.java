package no.comp.finalproject.service;

import java.util.List;

import no.comp.finalproject.domain.User;
import no.comp.finalproject.domain.constant.UserRole;

public interface UserService extends BaseService<User> {

	User read(Long userId);
	User read(String username);
	List<User> read(UserRole role);
	List<User> contains(String part);
	
}
