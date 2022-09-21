package com.example.mydaily.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.UserService;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.UserResponse;

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
		userToSave.setFriendsid(user.getFriendsid());
		
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
		
		User user = userdao.findByUserName(username);
		return user;
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
	public List<Optional<User>> getAllFriends(Long userid) {
		List<Optional<User>> list;
		
		User user = userdao.findById(userid).get();
		
		List<Long> friends = user.getFriendsid();
		list =friends.stream().map(e-> userdao.findById(e) ).collect(Collectors.toList());
		
		return list;
	}

	@Override
	public User addFriends(Long userid,Long friendsid) {
		Optional<User> user = userdao.findById(userid);
		Optional<User> friend = userdao.findById(friendsid);
		List<Long> list = new ArrayList<>();
		
		if(user.isPresent()&& friend.isPresent()) {
			User foundeduser = user.get();
			list.add(friend.get().getId());
			foundeduser.setFriendsid(list);
			
			return userdao.save(foundeduser);
		}
		
		return null;
	}

	@Override
	public void removeOneFriend(Long userid, Long friendsid) {
		Optional<User> user = userdao.findById(userid);
		Optional<User> friend = userdao.findById(friendsid);
		
	
		if(user.isPresent()&& friend.isPresent()) {
			User foundeduser = user.get();
			foundeduser.getFriendsid().remove(friend.get().getId());
			userdao.save(foundeduser);
	
		}
		

		
	}
	

}
