package com.example.mydaily.business.concretes;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mydaily.auth.JwtUserDetails;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.entities.User;

@Service
public class UserDetailServiceImp implements UserDetailsService {

	private UserDao userdao;
	
	public UserDetailServiceImp(UserDao userDao) {
		this.userdao=userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userdao.findByUserName(username);
		
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserById(Long id) {
		User user = userdao.findById(id).get();
		return JwtUserDetails.create(user);
	}
	
}
