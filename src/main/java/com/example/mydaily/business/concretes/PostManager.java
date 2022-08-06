package com.example.mydaily.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.PostService;
import com.example.mydaily.dataaccess.PostDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.PostUpdateRequest;
import com.example.mydaily.entities.Post;
import com.example.mydaily.entities.User;

@Service
public class PostManager implements PostService {
	
	private final PostDao postDao;
	private final UserDao userDao;
	
	public PostManager(PostDao postDao,UserDao userDao) {
		this.postDao=postDao;
		this.userDao=userDao;
	}

	@Override
	public List<Post> getAllPosts(Optional<Long> userid) {
		if(userid.isPresent()) {
			return postDao.findByUserId(userid);
		}
		return postDao.findAll();
	}

	@Override
	public Post getOnePostById(Long postid) {
		
		return postDao.findById(postid).orElseThrow();
	}

	@Override
	public void deletePost(Long postid) {
		postDao.deleteById(postid);
		
	}

	@Override
	public Post createOnePost(Post newPost) {
		Optional<User> user = userDao.findById(newPost.getUser().getId());
		
		if(user.isPresent()) {
			Post post = new Post();
			post.setId(newPost.getId());
			post.setText(newPost.getText());
			post.setDate(new Date());
			post.setUser(user.get());
			
			return postDao.save(post);
			
		}
		
		return null;
	}

	@Override
	public Post updateOnePost(Long postid, PostUpdateRequest request) {
		Optional<Post> post = postDao.findById(postid);
		
		if(post.isPresent()) {
			Post updatePost = post.get();
			updatePost.setText(request.getText());
			
			return postDao.save(updatePost);
		}
		
		return null;
	}

}
