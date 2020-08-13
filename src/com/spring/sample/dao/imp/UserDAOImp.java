package com.spring.sample.dao.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.spring.sample.dao.UserDAO;
import com.spring.sample.entity.User;
import com.spring.sample.service.imp.UserServiceImp;
import com.spring.sample.util.CommonUtil;

@Repository
public class UserDAOImp extends GenericDAOImp<User, Integer> implements UserDAO {
	private HibernateTemplate hibernateTemplate;
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	public UserDAOImp() {
		super(User.class);
	}

	public User findUser(User user) {
		log.info("Finding the user in the database");
		try {
			List<User> userList = (List<User>) hibernateTemplate.findByExample(user);
			if (!CommonUtil.isEmpty(userList)) {
				return userList.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details from the database", e);
			return null;
		}
	}
}
