package com.spring.sample.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.sample.dao.UserDAO;
import com.spring.sample.entity.User;
import com.spring.sample.model.UserModel;
import com.spring.sample.service.UserService;

@Component
public class UserServiceImp implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private UserDAO userDAO;

	private UserServiceImp() {
	}

	public void setUserDao(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserModel findUser(String email, String password) {
		log.info("Checking the user in the database");
		UserModel userModel = null;
		try {
			User user = userDAO.findUser(new User(email, password));
			userModel = UserModel.build(user);
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details from the database", e);
		}
		return userModel;
	}

	public UserModel findUser(Integer id) {
		log.info("Checking the user in the database");
		UserModel userModel = null;
		try {
			User user = userDAO.find(id);
			userModel = UserModel.build(user);
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details from the database", e);
		}
		return userModel;
	}

	@Transactional
	public UserModel addUser(UserModel userModel) throws Exception {
		log.info("Adding the user in the database");
		try {
			User user = userDAO.makePersistent(userModel.make());
			return UserModel.build(user);
		} catch (Exception e) {
			log.error("An error occurred while adding the user details to the database", e);
			throw e;
		}
	}

	@Transactional
	public UserModel editUser(UserModel userModel) throws Exception {
		log.info("Updating the user in the database");
		try {
			User user = userDAO.find(userModel.getId(), true);
			userModel.make(user);
			userDAO.makePersistent(user);
			return userModel;
		} catch (Exception e) {
			log.error("An error occurred while adding the user details to the database", e);
			throw e;
		}
	}

	@Transactional
	public boolean deleteUser(UserModel userModel) throws Exception {
		log.info("Deleting the user in the database");
		try {
			User user = userDAO.find(userModel.getId(), true);
			userDAO.makeTransient(user);
			return true;
		} catch (Exception e) {
			log.error("An error occurred while adding the user details to the database", e);
			throw e;
		}
	}

	public List<UserModel> findAll() {
		log.info("Fetching all users in the database");
		List<UserModel> userModelList = new ArrayList<UserModel>();
		try {
			List<User> userList = userDAO.findAll();
			for (User user : userList) {
				userModelList.add(UserModel.build(user));
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching all users from the database", e);
		}
		return userModelList;
	}
}
