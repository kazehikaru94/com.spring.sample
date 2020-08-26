package com.spring.sample.dao;

import com.spring.sample.entity.Micropost;

public interface MicropostDAO extends GenericDAO<Micropost, Integer> {
	public Micropost findMicropost(Micropost micropost);
}
