package com.spring.sample.model;

import javax.validation.constraints.NotEmpty;

import com.spring.sample.entity.Micropost;

public class MicropostModel {
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private Integer userId;
	@NotEmpty(message = "{micropost.validation.content.required}")
	private String content;
	
	public static MicropostModel build(Micropost micropost) {
		MicropostModel micropostModel = new MicropostModel();
		micropostModel.setId(micropost.getId());
		micropostModel.setUserId(micropost.getUserId());
		micropostModel.setContent(micropost.getContent());
		return micropostModel;
	}

	public Micropost make() {
		Micropost micropost = new Micropost();
		micropost.setId(this.getId());
		micropost.setUserId(this.getUserId());
		micropost.setContent(this.getContent());
		return micropost;
	}
	
	public void make(Micropost micropost) {
		micropost.setId(this.getId());
		micropost.setUserId(this.getUserId());
		micropost.setContent(this.getContent());
		
	}

}
