package com.zemoso.springbootassignment.service;


import com.zemoso.springbootassignment.dao.UsersRepository;
import com.zemoso.springbootassignment.entity.Authorities;
import com.zemoso.springbootassignment.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public void save(Users theUser) {
	    String username = theUser.getUsername();

        Authorities authority = new Authorities(username,"ROLE_CUSTOMER");

        theUser.setEnabled((short) 1);
	    theUser.bcryptPassword();
        theUser.addAuthority(authority);

		usersRepository.save(theUser);
	}

	@Override
    public Users findByUsername(String username){
	    return usersRepository.findByUsername(username);
    }
}
