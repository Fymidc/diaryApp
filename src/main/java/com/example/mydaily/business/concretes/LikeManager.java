package com.example.mydaily.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.LikeService;
import com.example.mydaily.dataaccess.LikeDao;
import com.example.mydaily.dataaccess.PostDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.LikeCreateRequest;
import com.example.mydaily.dtos.LikeResponse;
import com.example.mydaily.entities.Like;
import com.example.mydaily.entities.Post;
import com.example.mydaily.entities.User;

@Service
public class LikeManager implements LikeService {
	
	private final LikeDao likeDao;
	private final UserDao userDao;
	private final PostDao postDao;
	
	public LikeManager(LikeDao likeDao,UserDao userDao,PostDao postDao) {
		this.likeDao=likeDao;
		this.userDao=userDao;
		this.postDao=postDao;
	}
	
	@Override
	public List<LikeResponse> getAllLikes(Optional<Long> userid, Optional<Long> postid) {
		
		List<Like> list;
		if(userid.isPresent()&&postid.isPresent()) {
			list=likeDao.findByUserIdAndPostId(userid.get(),postid.get());
		}else if(userid.isPresent()) {
			list=likeDao.findByUserId(userid.get());
			
		}else if(postid.isPresent()) {
			list=likeDao.findByPostId(postid.get());
		}else {
			list = likeDao.findAll();
		}
		return list.stream().map(p-> new LikeResponse(p)).collect(Collectors.toList()); 
	}

	@Override
	public void deleteOneLike(Long userid) {
		likeDao.deleteById(userid);
		
	}

	@Override
	public Like createOneLike(LikeCreateRequest request) {
		User user = userDao.findById(request.getUserid()).get();
		Post post = postDao.findById(request.getPostid()).get();
		
		Like liketoSave = new Like();
		
		liketoSave.setId(request.getId());
		liketoSave.setUser(user);
		liketoSave.setPost(post);
		
		
		
		return likeDao.save(liketoSave);
	}

}
