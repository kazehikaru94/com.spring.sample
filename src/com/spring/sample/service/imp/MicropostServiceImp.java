package com.spring.sample.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.sample.dao.MicropostDAO;
import com.spring.sample.entity.Micropost;
import com.spring.sample.model.MicropostModel;
import com.spring.sample.service.MicropostService;

@Component
public class MicropostServiceImp implements MicropostService {

	private static Logger log = LoggerFactory.getLogger(MicropostServiceImp.class);

	@Autowired
	private MicropostDAO micropostDAO;

	private MicropostServiceImp() {
	}

	public void setMicropostDao(MicropostDAO micropostDAO) {
		this.micropostDAO = micropostDAO;
	}

	public MicropostModel findMicropost(Integer id) {
		log.info("Checking the micropost in the database");
		MicropostModel micropostModel = null;
		try {
			Micropost micropost = micropostDAO.findMicropost(new Micropost(id));
			micropostModel = MicropostModel.build(micropost);
		} catch (Exception e) {
			log.error("An error occurred while fetching the micropost details from the database", e);
		}
		return micropostModel;
	}

	public List<MicropostModel> findMicropostbyUserId(Integer userId) {
		log.info("Fetching the user by email in the database");
		try {
			List<Micropost> microposts = micropostDAO.findMicropostbyUserId(userId);
			log.info("microposts: " + microposts.size());
			List<MicropostModel> micropostModels = null;
			if (microposts != null) {
				micropostModels = new ArrayList<MicropostModel>();
				for (Micropost micropost : microposts) {
					MicropostModel micropostModel = new MicropostModel();
					BeanUtils.copyProperties(micropost, micropostModel);
					micropostModels.add(micropostModel);
				}
				log.info("micropostModels: " + micropostModels.size());
			}
			return micropostModels;
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details by email from the database", e);
			return null;
		}
	}

//TO-DO: find micropost by keyword
//	public MicropostModel findMicropost(String keyword) {
//		log.info("Checking the user in the database");
//		MicropostModel micropostModel = null;
//		try {
//			Micropost micropost = MicropostDAO.find(id);
//			userModel = UserModel.build(user);
//		} catch (Exception e) {
//			log.error("An error occurred while fetching the user details from the database", e);
//		}
//		return userModel;
//	}

//	Implement later nha
//	@Transactional
//	public MicropostModel addMicropost(MicropostModel micropostModel) throws Exception {
//		log.info("Adding the micropost in the database");
//		try {
//			Micropost micropost = MicropostDAO.makePersistent(micropostModel.make());
//			return UserModel.build(user);
//		} catch (Exception e) {
//			log.error("An error occurred while adding the user details to the database", e);
//			throw e;
//		}
//	}
//
//	@Transactional
//	public UserModel editUser(UserModel userModel) throws Exception {
//		log.info("Updating the user in the database");
//		try {
//			User user = userDAO.find(userModel.getId(), true);
//			userModel.make(user);
//			userDAO.makePersistent(user);
//			return userModel;
//		} catch (Exception e) {
//			log.error("An error occurred while adding the user details to the database", e);
//			throw e;
//		}
//	}
//
//	@Transactional
//	public boolean deleteUser(UserModel userModel) throws Exception {
//		log.info("Deleting the user in the database");
//		try {
//			User user = userDAO.find(userModel.getId(), true);
//			userDAO.makeTransient(user);
//			return true;
//		} catch (Exception e) {
//			log.error("An error occurred while adding the user details to the database", e);
//			throw e;
//		}
//	}
//
	public List<MicropostModel> findAll() {
		log.info("Fetching all micropost in the database");
		List<MicropostModel> micropostModelList = new ArrayList<MicropostModel>();
		try {
			List<Micropost> micropostList = micropostDAO.findAll();
			for (Micropost micropost : micropostList) {
				micropostModelList.add(MicropostModel.build(micropost));
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching all users from the database", e);
		}
		return micropostModelList;
	}
}
