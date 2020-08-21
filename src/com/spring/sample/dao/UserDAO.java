package com.spring.sample.dao;

import org.springframework.data.domain.Page;

import com.spring.sample.entity.User;
import com.spring.sample.model.UserModel;

public interface UserDAO extends GenericDAO<User, Integer> {
	public User findUser(User user);
	
	public User findUserByEmail(String email);
	
	public Page<UserModel> paginate(UserModel userModel);
}
