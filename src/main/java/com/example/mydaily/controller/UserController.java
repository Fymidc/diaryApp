package com.example.mydaily.controller;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydaily.business.abstracts.UserService;
import com.example.mydaily.dtos.UserResponse;
import com.example.mydaily.entities.Friends;
import com.example.mydaily.entities.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController (UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping
	public List<UserResponse> getAllUsers( ){
		return userService.getAllUsers();
	}

	@PostMapping
	public User createOneUser(@RequestBody User user) {
		return userService.createOneUser(user);
	}
	
	@GetMapping("/{id}")
	public User getOneUserById (@PathVariable Long id ) {
		return userService.getOneUserById(id);
	}
	
	@DeleteMapping("/{id}")
	void deleteOneUser(@PathVariable Long id) {
		userService.deleteOneUser(id);
	}
	
	@GetMapping("/name/{username}")
	public UserResponse getOneUserByUserName(@PathVariable String username) {
		return userService.getOneUserByUserName(username);
	}
	
	@PutMapping("/{id}")
	public User updateOneUser(@PathVariable Long id ,@RequestBody User request) {
		return userService.updateOneUser(id, request);
	}
	
	@GetMapping("/friends/{id}")
	public List<Friends> getAllFriends(@PathVariable Long id){
		return userService.getAllFriends(id);
	}
	
}
