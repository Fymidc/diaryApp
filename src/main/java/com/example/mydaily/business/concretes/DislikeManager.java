package com.example.mydaily.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.DislikeService;
import com.example.mydaily.dataaccess.DislikeDao;
import com.example.mydaily.dataaccess.PostDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.DislikeResponse;
import com.example.mydaily.entities.Dislike;
import com.example.mydaily.entities.Post;
import com.example.mydaily.entities.User;

@Service
public class DislikeManager implements DislikeService {
	
	private final DislikeDao dislikeDao;
	private final UserDao userDao;
	private final PostDao postDao;
	
	public DislikeManager(DislikeDao dislikeDao,UserDao userDao,PostDao postDao) {
		this.dislikeDao=dislikeDao;
		this.userDao=userDao;
		this.postDao=postDao;
	}
	
	@Override
	public List<DislikeResponse> getAllLikes(Optional<Long> userid, Optional<Long> postid) {
		
		List<Dislike> list;
		if(userid.isPresent()&&postid.isPresent()) {
			list=dislikeDao.findByUserIdAndPostId(userid.get(),postid.get());
		}else if(userid.isPresent()) {
			list=dislikeDao.findByUserId(userid.get());
			
		}else if(postid.isPresent()) {
			list=dislikeDao.findByPostId(postid.get());
		}else {
			list = dislikeDao.findAll();
		}
		return list.stream().map(p-> new DislikeResponse(p)).collect(Collectors.toList()); 
	}

	@Override
	public void deleteOneDisLike(Long userid) {
		dislikeDao.deleteById(userid);
		
	}

	@Override
	public Dislike createOneDisLike(Dislike request) {
		User user = userDao.findById(request.getUser().getId()).get();
		Post post = postDao.findById(request.getPost().getId()).get();
		
		Dislike liketoSave = new Dislike();
		
		liketoSave.setId(request.getId());
		liketoSave.setUser(user);
		liketoSave.setPost(post);
		
		
		
		return dislikeDao.save(liketoSave);
	}

}
