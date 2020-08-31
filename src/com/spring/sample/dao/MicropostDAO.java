package com.spring.sample.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.sample.entity.Micropost;

public interface MicropostDAO extends GenericDAO<Micropost, Integer> {
	public Micropost findMicropost(Micropost micropost);
	
	public List<Micropost> findMicropostbyUserId(Integer userId);
	
	public Page<Micropost> paginate (Micropost micropost, Pageable pageable);
}
