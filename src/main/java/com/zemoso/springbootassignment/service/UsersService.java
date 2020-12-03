package com.zemoso.springbootassignment.service;

import com.zemoso.springbootassignment.entity.Users;

public interface UsersService {

	public Users findByUsername(String username);
	
	public void save(Users theUser);
}
