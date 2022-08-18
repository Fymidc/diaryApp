package com.example.mydaily.dtos;

import com.example.mydaily.entities.User;

import lombok.Data;

@Data
public class UserResponse {
	private Long id;
	private String userName;
	
	
	public UserResponse(User entity) {
		this.id=entity.getId();
		this.userName=entity.getUserName();
		
	}
}
