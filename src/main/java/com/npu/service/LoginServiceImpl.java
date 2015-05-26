package com.npu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.npu.dao.CategoryProductDAO;
import com.npu.dao.UserDAO;
import com.npu.domain.User;

@Service("authentication")
public class LoginServiceImpl implements LoginService {

	@Autowired
	@Qualifier("userDaoJdbc")
	private UserDAO userDAO;

	@Autowired
	@Qualifier("categoryProductDaoJdbc")
	CategoryProductDAO categoryProd;

	@Override
	public boolean authenticateUser(String userId, String passcode) {
		User userFromDb = null;
		try {
			userFromDb = userDAO.authenticateUser(userId, passcode);
		} catch (EmptyResultDataAccessException e) {
			// do nothing
		}
		
		if(userFromDb  != null ){
			return true;
		}else{
			return false;	
		}
		
	}

	@Override
	public User getUser(String userId) {

		return userDAO.getUser(userId);
	}

	@Override
	public void insertUser(User user) {
		userDAO.insertUser(user);
	}

}
