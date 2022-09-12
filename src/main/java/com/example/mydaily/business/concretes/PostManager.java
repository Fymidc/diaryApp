package com.example.mydaily.business.concretes;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.PostService;
import com.example.mydaily.dataaccess.CommentDao;
import com.example.mydaily.dataaccess.LikeDao;
import com.example.mydaily.dataaccess.PostDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.PostCreateRequest;
import com.example.mydaily.dtos.PostResponse;
import com.example.mydaily.dtos.PostUpdateRequest;
import com.example.mydaily.entities.Comment;
import com.example.mydaily.entities.Post;
import com.example.mydaily.entities.User;

@Service
public class PostManager implements PostService {
	
	private final PostDao postDao;
	private final UserDao userDao;
	private final CommentDao commentDao;
	private final LikeDao likeDao;
	private Long amount;
	
	public PostManager(PostDao postDao,UserDao userDao,CommentDao commentDao,LikeDao likeDao) {
		this.postDao=postDao;
		this.userDao=userDao;
		this.commentDao=commentDao;
		this.likeDao = likeDao;
	}

	@Override
	public List<PostResponse> getAllPosts(Optional<Long> userid) {
		
		List<Post> list;
		
		
		
		if(userid.isPresent()) {
			list= postDao.findByUserId(userid);
		}else {
			list = postDao.findAll();
			
					
		}
		
		return list.stream().map(e-> new PostResponse(e,commentDao,likeDao)).collect(Collectors.toList());
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
	public Post createOnePost(PostCreateRequest newPost) {
		Optional<User> user = userDao.findById(newPost.getUserid());
		
		
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
