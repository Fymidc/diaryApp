package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.dtos.PostUpdateRequest;
import com.example.mydaily.entities.Post;



public interface PostService {

	List<Post> getAllPosts(Optional<Long> userid);
	Post getOnePostById(Long postid);
	void deletePost(Long postid);
	Post createOnePost(Post newPost);
	Post updateOnePost(Long postid,PostUpdateRequest request);
	
}
