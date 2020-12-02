package com.zemoso.springbootassignment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.zemoso.springbootassignment.entity.UserDetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
public class UserDetailsDAOImpl implements UserDetailsDAO {

	private Connection getConnection() {
		String jdbcUrl = "jdbc:mysql://localhost:3306/restaurant_directory?useSSL=false";
		String user = "hbstudent";
		String pass = "hbstudent";
		try {
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			return myConn;
		}
		catch(Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}	
		return null;
	}
	
	/*
	private boolean isUsernameExists(String username) {
		return false;
	}
	 */
	
	@Override
	public void save(UserDetails theUser) {
		Connection myConn;
		try {			
			myConn = getConnection();
			
			Statement theUserQuery = myConn.createStatement();
			
			Statement theAuthorityQuery = myConn.createStatement();
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        
			String encrptedPassword = "{bcrypt}"+ passwordEncoder.encode(theUser.getPassword());
			
			theUserQuery.executeUpdate("INSERT INTO restaurant_directory.users VALUES ('"
							+ theUser.getUsername()+"','"
							+ encrptedPassword +"',1);");
			
			
			theUserQuery.executeUpdate("INSERT INTO authorities\r\n VALUES \r\n ('"
							+ theUser.getUsername()+ "','ROLE_CUSTOMER');");
			
			theUserQuery.close();
			
			theAuthorityQuery.close();
			
			myConn.close();
			
		}
		catch(Exception e) {
			System.out.println("Query failed");
			e.printStackTrace();
		}

	}

}
