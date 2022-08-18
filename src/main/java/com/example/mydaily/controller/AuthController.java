package com.example.mydaily.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydaily.auth.TokenManager;
import com.example.mydaily.business.abstracts.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthenticationManager authenticationManager;

    private TokenManager tokenManager;

    private UserService userService;
    
    public AuthController (AuthenticationManager authenticationManager,
    		TokenManager tokenManager,UserService userService) {
    	
    	this.authenticationManager=authenticationManager;
    	this.tokenManager=tokenManager;
    	this.userService=userService;
    }
    
    
    
    
    
    
}
