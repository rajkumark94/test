package com.sample.hibernate.service;

import java.util.List;
import java.util.Optional;

import com.sample.hibernate.model.User;

public interface UserService {

	User addUser(User user);

	List<User> loadAllUser();

	Optional<User> loadUserById(long userId);

	User updateUser(User user);

	void deleteUser(long userId);
}
