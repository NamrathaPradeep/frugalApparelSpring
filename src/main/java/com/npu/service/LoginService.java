package com.npu.service;

import com.npu.domain.User;

public interface LoginService {

	public boolean authenticateUser(String userId, String passcode);

	public User getUser(String userId);
	
	public void insertUser(User user);

}
