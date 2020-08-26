package com.spring.sample.dao.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.spring.sample.dao.MicropostDAO;
import com.spring.sample.entity.Micropost;
import com.spring.sample.service.imp.UserServiceImp;
import com.spring.sample.util.CommonUtil;

@Repository
public class MicropostDAOImp extends GenericDAOImp<Micropost, Integer> implements MicropostDAO{
	private HibernateTemplate hibernateTemplate;
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	public MicropostDAOImp() {
		super(Micropost.class);
	}

	public Micropost findMicropost(Micropost micropost) {
		log.info("Finding the user in the database");
		try {
			List<Micropost> micropostList = (List<Micropost>) hibernateTemplate.findByExample(micropost);
			if (!CommonUtil.isEmpty(micropostList)) {
				return micropostList.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching the micropost details from the database", e);
			return null;
		}
	}
}
