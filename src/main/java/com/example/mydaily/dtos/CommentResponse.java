package com.example.mydaily.dtos;

import com.example.mydaily.entities.Comment;

import lombok.Data;

@Data
public class CommentResponse {

	private Long id;
	private String text;
	private Long userId;
	private String userName;
	private Long postId;
	private Long diaryId;
	
	public CommentResponse(Comment entity) {
		this.id = entity.getId();
		this.text = entity.getText();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUserName();
		this.postId = entity.getPost().getId();
		this.diaryId = entity.getDiary().getId();
	}
}
