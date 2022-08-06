package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.entities.User;

public interface UserService {
	List<User> getAllUsers(Optional<Long> userid);
	User createOneUser(User user);
	User getOneUserById(Long userid);
	void deleteOneUser(Long userid);
	User getOneUserByUserName(String username);
	User updateOneUser(Long userid,User newUser);
}
