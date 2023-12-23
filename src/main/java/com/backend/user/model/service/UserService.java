package com.backend.user.model.service;

public interface UserService {
	String login(String userPassword) throws Exception;
	void changePassword(String password,String changePassword) throws Exception;
}
