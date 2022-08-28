package com.example.mydaily.dtos;


import com.example.mydaily.entities.Comment;

import lombok.Data;

@Data
public class CommentResponseDiary {

	private Long id;
	private String text;
	private Long userId;
	private String userName;
	private Long diaryId;
	
	
	
	public CommentResponseDiary(Comment entity) {
		this.id = entity.getId();
		this.text = entity.getText();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUserName();
		this.diaryId = entity.getDiary().getId();
		
	}
	
}
