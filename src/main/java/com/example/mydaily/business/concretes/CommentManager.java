package com.example.mydaily.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.CommentService;
import com.example.mydaily.dataaccess.CommentDao;
import com.example.mydaily.dataaccess.DiaryDao;
import com.example.mydaily.dataaccess.PostDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.CommentCreateRequest;
import com.example.mydaily.dtos.CommentResponseDiary;
import com.example.mydaily.dtos.CommentResponsePost;
import com.example.mydaily.dtos.CommentUpdateRequest;
import com.example.mydaily.entities.Comment;
import com.example.mydaily.entities.Diary;
import com.example.mydaily.entities.Post;
import com.example.mydaily.entities.User;

@Service
@Transactional
public class CommentManager implements CommentService{
	
	private final CommentDao commentDao;
	private final UserDao userDao;
	private final PostDao postDao;
	private final DiaryDao diaryDao;
	
	
	
	public CommentManager(CommentDao commentDao,UserDao userDao,PostDao postDao,DiaryDao diaryDao) {
		this.commentDao=commentDao;
		this.userDao=userDao;
		this.postDao = postDao;
		this.diaryDao = diaryDao;
		
				
	}

	@Override
	public List<CommentResponsePost> getAllCommentsPost(Optional<Long> userid,Optional<Long> postid) {
		
		List<Comment> list;
		
		if(userid.isPresent()) {
			list=commentDao.findByUserId(userid.get());
		}else if(postid.isPresent()) {
			list = commentDao.findByPostId(postid.get());
		}else {
			list = commentDao.findAll();
		}
		
		
		return list.stream().map(p -> new CommentResponsePost(p)).collect(Collectors.toList());
	}
	
	@Override
	public List<CommentResponseDiary> getAllCommentsDiary(Optional<Long> userid,Optional<Long> diaryid) {
		
		List<Comment> list;
		
		if(userid.isPresent()) {
			list=commentDao.findByUserId(userid.get());
		}else if(diaryid.isPresent()) {
			list = commentDao.findByDiaryId(diaryid.get());
		}else {
			list = commentDao.findAll();
		}
		
		
		return list.stream().map(p -> new CommentResponseDiary(p)).collect(Collectors.toList());
	}

	@Override
	public void deleteOneComment(Long commentid) {
		commentDao.deleteById(commentid);		
	}

	@Override
	public Comment createOneComment(Optional<Long> postid,Optional<Long> diaryid,CommentCreateRequest request) {
		Optional<User> user = userDao.findById(request.getUserid());
		
		Post post = postDao.findById(postid);
		
 		Diary diary = diaryDao.findById(diaryid);
 	
 		if(user.isPresent()) {
 			Comment comment = new Comment();
 			
 			comment.setId(request.getId());
 			comment.setText(request.getText());
 			comment.setDate(request.getDate());
 			comment.setUser(user.get());
 			comment.setPost(post);
 			comment.setDiary(diary);
 			
 			return	commentDao.save(comment);
 		}
 			
 	
		
		return null;
	}

	@Override
	public Comment updateOneComment(Long commentid, CommentUpdateRequest request) {
		
		Optional<Comment> comment = commentDao.findById(commentid);
		
		if(comment.isPresent()) {
			Comment commentToUpdate = comment.get();
			commentToUpdate.setText(request.getText());
			
			return commentDao.save(commentToUpdate);
		}
		
		return null;
	}

}
