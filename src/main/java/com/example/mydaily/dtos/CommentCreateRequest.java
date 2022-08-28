package com.example.mydaily.dtos;

import java.util.Date;



import com.example.mydaily.entities.Comment;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CommentCreateRequest {

	private Long id;
	private String text;
	private Date date;
	
	
	private Long userid;
	/*
	public CommentCreateRequest (Comment entity) {
		this.id = entity.getId();
		this.text = entity.getText();
		this.date = entity.getDate();
		this.postid = entity.getPost().getId();
		this.diaryid = entity.getDiary().getId();
		this.userid = entity.getUser().getId();
	}
	*/
}
