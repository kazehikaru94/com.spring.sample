package com.spring.sample.entity;

public class Micropost {
	private Integer id;
	private Integer userId;
	private String content;
	private User user;


	public void setUser(User user) {
		this.user = user;
	}

	public Micropost() {
	}
	
	public Micropost(Integer id) {
		this.id = id;
	}

	public Micropost(String content) {
		this.content = content;
	}
	
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
	public User getUser() {
		return user;
	}
}
