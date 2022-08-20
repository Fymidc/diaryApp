package com.example.mydaily.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydaily.auth.TokenManager;
import com.example.mydaily.business.abstracts.UserService;
import com.example.mydaily.dtos.AuthResponse;
import com.example.mydaily.dtos.UserRequest;

import com.example.mydaily.entities.User;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthenticationManager authenticationManager;

    private TokenManager tokenManager;

    private UserService userService;
    
    private BCryptPasswordEncoder encoder;
    
    public AuthController (AuthenticationManager authenticationManager,
    		TokenManager tokenManager,UserService userService,BCryptPasswordEncoder encoder) {
    	
    	this.authenticationManager=authenticationManager;
    	this.tokenManager=tokenManager;
    	this.userService=userService;
    	this.encoder=encoder;
    }
    
    
    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest request) {
    	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
    	Authentication auth = authenticationManager.authenticate(authToken);
    	SecurityContextHolder.getContext().setAuthentication(auth);
    	String jwtToken = tokenManager.generateToken(auth);
    	
    	User user = userService.getOneUserByUserName(request.getUserName());
    	AuthResponse authResponse = new AuthResponse();
    	authResponse.setMessage("Bearer "+ jwtToken);
    	authResponse.setUserId(user.getId());
    	
    	return authResponse;
    	
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest request){
    	AuthResponse authResponse = new AuthResponse();
    	if(userService.getOneUserByUserName(request.getUserName()) != null) {
    		//bu isimde bir kullanıcı var ise 
    		authResponse.setMessage("Username already taken");
    		return new ResponseEntity<>(authResponse,HttpStatus.BAD_REQUEST);
    	}
    	
    	User user = new User();
    	user.setUserName(request.getUserName());
    	user.setPassword(encoder.encode(request.getPassword()));
    	
    	
    	userService.createOneUser(user);
    	authResponse.setMessage("User succesfully registered");
    	
    	return new ResponseEntity<>(authResponse,HttpStatus.CREATED);
    	
    	
    	
    }
    
    //postmande login register dene
    
}
