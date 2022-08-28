package com.example.mydaily.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydaily.business.abstracts.LikeService;
import com.example.mydaily.dtos.LikeCreateRequest;
import com.example.mydaily.dtos.LikeResponse;
import com.example.mydaily.entities.Like;

@RestController
@RequestMapping("/likes")
public class LikeController {

	private final LikeService likeService;
	
	public LikeController(LikeService likeService) {
		this.likeService=likeService;
	}
	
	@GetMapping
	public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userid , @RequestParam Optional<Long> postid){
		return likeService.getAllLikes(userid, postid);
	}
	
	@DeleteMapping("/{id}")
	void deleteOneLike( @PathVariable Long id ) {
		 likeService.deleteOneLike(id);
	}
	
	@PostMapping
	public Like createOneLike(@RequestBody LikeCreateRequest request) {
		return likeService.createOneLike(request);
	}
}
