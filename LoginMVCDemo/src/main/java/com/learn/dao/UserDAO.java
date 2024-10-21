package com.learn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.learn.bean.UserBean;

public class UserDAO {


	public UserBean getUserBean(UserBean userbean) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/vrunds_db","root","root2511");
			
			PreparedStatement preparedstatement = connection.prepareStatement("select * from user where username=? and password=?");
			preparedstatement.setString(1, userbean.getUsername());
			preparedstatement.setString(2, userbean.getPassword());
			
			ResultSet resultset = preparedstatement.executeQuery();
			
			while(resultset.next()) {
				userbean.setFirstName(resultset.getString(3));
				userbean.setLastName(resultset.getString(4));
				
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userbean;
	}

}
