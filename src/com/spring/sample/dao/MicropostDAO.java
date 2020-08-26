package com.spring.sample.dao;

import java.util.List;

import com.spring.sample.entity.Micropost;

public interface MicropostDAO extends GenericDAO<Micropost, Integer> {
	public Micropost findMicropost(Micropost micropost);
	
	public List<Micropost> findMicropostbyUserId(Integer userId);
}
