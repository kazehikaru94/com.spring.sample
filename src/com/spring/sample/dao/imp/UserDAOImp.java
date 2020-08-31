package com.spring.sample.dao.imp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.spring.sample.dao.UserDAO;
import com.spring.sample.entity.User;
import com.spring.sample.model.UserModel;
import com.spring.sample.service.imp.UserServiceImp;
import com.spring.sample.util.CommonUtil;
import com.spring.sample.util.SearchQueryTemplate;

@Repository
public class UserDAOImp extends GenericDAOImp<User, Integer> implements UserDAO {
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	public UserDAOImp() {
		super(User.class);
	}

	public User findUser(User user) {
		log.info("Finding the user in the database");
		try {
			List<User> userList = (List<User>) getHibernateTemplate().findByExample(user);
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

	public User findUserByEmail(String email) {
		log.info("Finding the user by email in the database");
		try {
			return getHibernateTemplate().execute(new HibernateCallback<User>() {
				public User doInHibernate(Session session) throws HibernateException {
					Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
					query.setParameter("email", email);
					return query.uniqueResult();
				}
			});
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details by email from the database", e);
			return null;
		}
	}

	@Override
	public Page<UserModel> paginate(UserModel userModel) {
//		log.info("Paging the users in the database");
//		try {
//			SearchQueryTemplate searchQueryTemplate = new SearchQueryTemplate();
//			searchQueryTemplate.setSql("FROM User");
//			searchQueryTemplate.setCountSql("SELECT COUNT(*) FROM User");
//			searchQueryTemplate.setPageable(userModel.getPageable());
//			Page<User> users = find(searchQueryTemplate);
//			return users.map(user -> {
//				UserModel model = new UserModel();
//				BeanUtils.copyProperties(user, model);
//				return model;
//			});
//		} catch (Exception e) {
//			log.error("An error occurred while paging the user details from the database", e);
			return null;
//		}
	}
}
