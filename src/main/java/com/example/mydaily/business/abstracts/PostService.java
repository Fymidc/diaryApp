package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.dtos.PostCreateRequest;
import com.example.mydaily.dtos.PostResponse;
import com.example.mydaily.dtos.PostUpdateRequest;
import com.example.mydaily.entities.Post;



public interface PostService {

	List<PostResponse> getAllPosts(Optional<Long> id);
	
	Post getOnePostById(Long postid);
	void deletePost(Long postid);
	Post createOnePost(PostCreateRequest newPost);
	Post updateOnePost(Long postid,PostUpdateRequest request);
	
}
