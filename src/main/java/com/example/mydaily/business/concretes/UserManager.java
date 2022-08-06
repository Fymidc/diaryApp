package com.example.mydaily.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.UserService;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.entities.User;

@Service
public class UserManager implements UserService {

	private final UserDao userdao;
	
	public UserManager(UserDao userDao) {
		this.userdao=userDao;
	}
	
	@Override
	public List<User> getAllUsers(Optional<Long> userid) {
		return userdao.findAll();
	}

	@Override
	public User createOneUser(User user) {
		
		User userToSave = new User();
		userToSave.setId(user.getId());
		userToSave.setUserName(user.getUserName());
		userToSave.setPassword(user.getPassword());
		userToSave.setBios(user.getBios());
		userToSave.setFriends(user.getFriends());
		
		return userdao.save(userToSave);
	}

	@Override
	public User getOneUserById(Long userid) {
		return userdao.findById(userid).orElseThrow();
	}

	@Override
	public void deleteOneUser(Long userid) {
		userdao.deleteById(userid);
		
	}

	@Override
	public User getOneUserByUserName(String username) {
		
		return userdao.findByUserName(username);
	}

	@Override
	public User updateOneUser(Long userid, User newUser) {
		Optional<User> user = userdao.findById(userid);
		
		if(user.isPresent()) {
			User foundedUser = user.get();
			foundedUser.setUserName(newUser.getUserName());
			foundedUser.setPassword(newUser.getPassword());
			foundedUser.setBios(newUser.getBios());
			
			userdao.save(foundedUser);
			
			return foundedUser;
		}
		
		return null;
	}
	

}
