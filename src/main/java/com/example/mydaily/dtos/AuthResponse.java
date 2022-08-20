package com.example.mydaily.dtos;

import lombok.Data;

@Data
public class AuthResponse {
	private String message;
	private Long userId;
}
