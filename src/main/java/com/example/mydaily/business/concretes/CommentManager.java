package com.example.mydaily.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.CommentService;
import com.example.mydaily.dataaccess.CommentDao;
import com.example.mydaily.dtos.CommentResponse;
import com.example.mydaily.dtos.CommentUpdateRequest;
import com.example.mydaily.entities.Comment;

@Service
public class CommentManager implements CommentService{
	
	private final CommentDao commentDao;
	
	
	public CommentManager(CommentDao commentDao) {
		this.commentDao=commentDao;
	}

	@Override
	public List<CommentResponse> getAllComments(Optional<Long> userid,Optional<Long> postid,Optional<Long> diaryid) {
		
		List<Comment> list;
		
		if(userid.isPresent()) {
			list=commentDao.findByUserId(userid.get());
		}else if(postid.isPresent()) {
			list = commentDao.findByPostId(postid.get());
		}else if (diaryid.isPresent()) {
			list = commentDao.findByDiaryId(diaryid.get());
		}else {
			list = commentDao.findAll();
		}
		
		
		return list.stream().map(p -> new CommentResponse(p)).collect(Collectors.toList());
	}

	@Override
	public void deleteOneComment(Long commentid) {
		commentDao.deleteById(commentid);		
	}

	@Override
	public Comment createOneComment(Comment request) {
		Comment comment = new Comment();
		
		comment.setId(request.getId());
		comment.setText(request.getText());
		
		
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
