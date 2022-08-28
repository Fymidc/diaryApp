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

import com.example.mydaily.business.abstracts.CommentService;
import com.example.mydaily.dtos.CommentCreateRequest;
import com.example.mydaily.dtos.CommentResponseDiary;
import com.example.mydaily.dtos.CommentResponsePost;
import com.example.mydaily.dtos.CommentUpdateRequest;
import com.example.mydaily.entities.Comment;

@RestController
@RequestMapping("/comments")
public class CommentController {

	private final CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService=commentService;
	}
	
	@GetMapping
	public List<CommentResponsePost> getAllCommentsPost(@RequestParam Optional<Long> userid ,@RequestParam Optional<Long> postid ){
		
		return commentService.getAllCommentsPost(userid, postid);
		
	}
	

	@GetMapping("/diary")
	public List<CommentResponseDiary> getAllCommentsDiary(@RequestParam Optional<Long> userid  ,@RequestParam Optional<Long> diaryid){
		return commentService.getAllCommentsDiary(userid, diaryid);
	}
	
	
	@DeleteMapping("/{id}")
	void deleteOneComment(@PathVariable Long id) {
		commentService.deleteOneComment(id);
	}
	
	@PostMapping
	public Comment createOneComment(@RequestParam Optional<Long> postid,@RequestParam Optional<Long> diaryid ,@RequestBody CommentCreateRequest request) {
		return commentService.createOneComment(postid,diaryid,request);
	}
	
	@PutMapping("/{id}")
	public Comment updateOneComment(@PathVariable Long id,@RequestBody CommentUpdateRequest request) {
		return commentService.updateOneComment(id, request);
	}
	
}
