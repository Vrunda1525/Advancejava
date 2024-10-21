package com.learn.service;

import com.learn.bean.UserBean;
import com.learn.dao.UserDAO;

public class UserService {

	UserDAO userdao = new UserDAO();
	
	public String authenticateAndPopulateUser(UserBean userbean) {
		// TODO Auto-generated method stub
		
		String error = null;
		userbean = userdao.getUserBean(userbean);
		
		if(userbean.getFirstName()==null) {
			error = "Invalid user Credentials";
		}
		
		return error;
		
	}

}
