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

import com.example.mydaily.business.abstracts.DislikeService;
import com.example.mydaily.dtos.DislikeResponse;
import com.example.mydaily.entities.Dislike;

@RestController
@RequestMapping("/dislikes")
public class DislikeController {

	private final DislikeService dislikeService;
	
	public DislikeController(DislikeService dislikeService) {
		this.dislikeService=dislikeService;
	}
	
	@GetMapping
	public List<DislikeResponse> getAllLikes(@RequestParam Optional<Long> userid , @RequestParam Optional<Long> postid){
		return dislikeService.getAllLikes(userid, postid);
	}
	
	@DeleteMapping("/{id}")
	void deleteOneLike( @PathVariable Long id ) {
		dislikeService.deleteOneDisLike(id);
	}
	
	@PostMapping
	public Dislike createOneLike(@RequestBody Dislike request) {
		return dislikeService.createOneDisLike(request);
	}
}
