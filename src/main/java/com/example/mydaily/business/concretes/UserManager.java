package com.example.mydaily.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.UserService;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.UserResponse;
import com.example.mydaily.entities.Friends;
import com.example.mydaily.entities.User;

@Service
public class UserManager implements UserService {

	private final UserDao userdao;
	
	public UserManager(UserDao userDao) {
		this.userdao=userDao;
	}
	
	@Override
	public List<UserResponse> getAllUsers() {
		List<User> list;
		list = userdao.findAll();
		return list.stream().map(r-> new UserResponse(r)).collect(Collectors.toList());
	}

	@Override
	public User createOneUser(User user) {
		
		User userToSave = new User();
		userToSave.setId(user.getId());
		userToSave.setUserName(user.getUserName());
		userToSave.setPassword(user.getPassword());
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
	public UserResponse getOneUserByUserName(String username) {
		
		User user = userdao.findByUserName(username);
		return new UserResponse(user);
	}

	@Override
	public User updateOneUser(Long userid, User newUser) {
		Optional<User> user = userdao.findById(userid);
		
		if(user.isPresent()) {
			User foundedUser = user.get();
			foundedUser.setUserName(newUser.getUserName());
			foundedUser.setPassword(newUser.getPassword());
			
			userdao.save(foundedUser);
			
			return foundedUser;
		}
		
		return null;
	}

	@Override
	public List<Friends> getAllFriends(Long userid) {
		User user = userdao.findById(userid).get();
		
		List<Friends> friends = user.getFriends();
		
		return friends;
	}
	

}
