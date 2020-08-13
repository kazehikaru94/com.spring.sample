package com.spring.sample.service;

import java.util.List;

import com.spring.sample.entity.User;

public interface UserService {
	public User findUser(String email, String password);

	public User findUser(Integer id);
	
	public User addUser(User user) throws Exception;
	
	public List<User> findAll();
}
