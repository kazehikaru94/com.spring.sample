package com.spring.sample.service;

import java.util.List;

import com.spring.sample.model.UserModel;

public interface UserService {
	public UserModel findUser(String email, String password);

	public UserModel findUser(Integer id);

	public UserModel addUser(UserModel user) throws Exception;
	
	public UserModel editUser(UserModel userModel) throws Exception;
	
	public boolean deleteUser(UserModel userModel) throws Exception;

	public List<UserModel> findAll();
}
