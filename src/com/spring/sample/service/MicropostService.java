package com.spring.sample.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.spring.sample.model.MicropostModel;

public interface MicropostService {
	
	public MicropostModel findMicropost(Integer id);

//	Implement cac function nay sau
//	public MicropostModel findMicropost(String keyword);
//	public MicropostModel addMicropost(MicropostModel micropostModel) throws Exception;
//	
//	public MicropostModel editMicropost(MicropostModel micropostModel) throws Exception;
//	
//	public boolean deleteMicropost(MicropostModel micropostModel) throws Exception;
//
	public List<MicropostModel> findAll();
	
	public List<MicropostModel> findMicropostbyUserId(Integer userId);
	
	public Page<MicropostModel> paginate (MicropostModel micropostModel);
}
