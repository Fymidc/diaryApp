package com.example.mydaily.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydaily.business.abstracts.PostService;
import com.example.mydaily.dtos.PostUpdateRequest;
import com.example.mydaily.entities.Post;

@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;
	
	public PostController(PostService postService) {
		this.postService= postService;
	}
	
	@GetMapping
	public List<Post> getAllPosts(@RequestParam Optional<Long> userid){
		return postService.getAllPosts(userid);
	}
	
	@GetMapping("/{id}")
	public Post getOnePostById (@PathVariable Long id) {
		return postService.getOnePostById(id);
	}
	@DeleteMapping("/{id}")
	void deletePost(@PathVariable Long id) {
		postService.deletePost(id);
	}
	
	@PostMapping
	public Post createOnePost(@RequestBody Post request) {
		return postService.createOnePost(request);
	}
	
	@PutMapping("/{id}")
	public Post updateOnePost(@PathVariable Long id,@RequestBody PostUpdateRequest request) {
		return postService.updateOnePost(id, request);
	}
}
