package com.npu.dao;

import com.npu.domain.User;
import com.npu.exceptions.UnknownTableEntryException;

public interface UserDAO {

	public void insertUser(User user) ;

	public void updateUser(User user);

	public void deleteUser(String user_id) throws UnknownTableEntryException;;
	
	public User getUser(String userId);
	
	public int getUserCount();
	
	public User authenticateUser(String userId, String passcode);
	
	
}

