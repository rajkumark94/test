package com.sample.hibernate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.hibernate.model.User;
import com.sample.hibernate.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> loadAllUser() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public Optional<User> loadUserById(long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}

}
