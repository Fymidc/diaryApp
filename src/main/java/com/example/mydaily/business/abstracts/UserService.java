package com.example.mydaily.business.abstracts;


import java.util.List;

import com.example.mydaily.dtos.UserResponse;
import com.example.mydaily.entities.Friends;
import com.example.mydaily.entities.User;

public interface UserService {
	List<UserResponse> getAllUsers();
	User createOneUser(User user);
	User getOneUserById(Long userid);
	void deleteOneUser(Long userid);
	UserResponse getOneUserByUserName(String username);
	User updateOneUser(Long userid,User newUser);
	List<Friends> getAllFriends(Long userid);
}
