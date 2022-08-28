package com.example.mydaily.dtos;

import java.util.List;

import com.example.mydaily.entities.User;

import lombok.Data;

@Data
public class UserResponse {
	private Long id;
	private String userName;
	private List<Long> friendsid;
	
	
	public UserResponse(User entity) {
		this.id=entity.getId();
		this.userName=entity.getUserName();
		this.friendsid=entity.getFriendsid();
	}
}
