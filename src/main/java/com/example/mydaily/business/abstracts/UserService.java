package com.example.mydaily.business.abstracts;


import java.util.List;
import java.util.Optional;

import com.example.mydaily.dtos.UserResponse;
import com.example.mydaily.entities.User;

public interface UserService {
	List<UserResponse> getAllUsers();
	User createOneUser(User user);
	User getOneUserById(Long userid);
	void deleteOneUser(Long userid);
	User getOneUserByUserName(String username);
	User updateOneUser(Long userid,User newUser);
	List<Optional<User>> getAllFriends(Long userid);
	User addFriends(Long userid,Long friednsid);
	void removeOneFriend(Long userid , Long friendsid);
}
