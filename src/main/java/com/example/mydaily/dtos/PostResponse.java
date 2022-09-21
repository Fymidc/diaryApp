package com.example.mydaily.dtos;



import java.util.List;
import java.util.stream.Collectors;

import com.example.mydaily.dataaccess.CommentDao;
import com.example.mydaily.dataaccess.LikeDao;
import com.example.mydaily.entities.Like;
import com.example.mydaily.entities.Post;

import lombok.Data;

@Data
public class PostResponse {

	private Long id;
	private String text;
	private String date;
	private String username;
	private Long userid;
	private Long commentAmount;
	private Long likeAmount;
	private List<Long> userids;

	
	
	
	public PostResponse(Post entity,CommentDao commentdao,LikeDao likeDao) {
		this.id=entity.getId();
		this.text=entity.getText();
		this.date=entity.getDate().toString();
		this.username=entity.getUser().getUserName();
		this.userid = entity.getUser().getId();
		this.commentAmount = (long) commentdao.findAllCommentByPostId(id).size();
		this.likeAmount = (long) likeDao.findAllLikeByPostId(id).size();
		this.userids = likeDao.findAllUserIdByPostId(id).stream().map(e->e.getUser().getId()).collect(Collectors.toList());
	}
}
