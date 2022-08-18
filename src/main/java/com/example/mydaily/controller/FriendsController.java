package com.example.mydaily.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydaily.business.abstracts.FriendsService;
import com.example.mydaily.entities.Friends;

@RestController
@RequestMapping("/friends")
public class FriendsController {

	private final FriendsService friendsService;
	
	public FriendsController(FriendsService friendsService) {
		this.friendsService=friendsService;
		
	}
	

	@PostMapping
	public Friends AddFriedns(@RequestParam Long userid,@RequestParam Long friendsid  ) {
		return friendsService.AddFriedns(userid, friendsid);
	}
	
	@DeleteMapping("/{id}")
	void deleteFriend(@PathVariable Long id) {
		friendsService.deleteFriend(id);
	}
	
	@GetMapping("/{id}")
	public Friends getOneFriendsById(@PathVariable Long id) {
		return friendsService.getOneFriendsById(id);
	}
	
	@GetMapping("/name/{name}")
	public Friends getOneFriendsByName(@PathVariable String name) {
		return friendsService.getOneFriendsByName(name);
	}
}
