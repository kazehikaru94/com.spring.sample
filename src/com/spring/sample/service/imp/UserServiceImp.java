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

	public User findUser(String email, String password) {
		log.info("Checking the user in the database");
		User user = null;
		try {
			user = userDAO.findUser(new User(email, password));
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details from the database", e);
		}
		return user;
	}

	public User findUser(Integer id) {
		log.info("Checking the user in the database");
		User user = null;
		try {
			user = userDAO.find(id);
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details from the database", e);
		}
		return user;
	}

	@Transactional
	public User addUser(User user) throws Exception {
		log.info("Adding the user in the database");
		try {
			return userDAO.makePersistent(user);
		} catch (Exception e) {
			log.error("An error occurred while adding the user details to the database", e);
			throw e;
		}
	}
	
	public List<User> findAll() {
		log.info("Fetching all users in the database");
		List<User> userList = new ArrayList<User>();
		try {
			userList = userDAO.findAll();
		} catch (Exception e) {
			log.error("An error occurred while fetching all users from the database", e);
		}
		return userList;
	}
}
