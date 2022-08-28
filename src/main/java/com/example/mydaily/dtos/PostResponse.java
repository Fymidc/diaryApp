package com.example.mydaily.dtos;



import com.example.mydaily.entities.Post;

import lombok.Data;

@Data
public class PostResponse {

	private Long id;
	private String text;
	private String date;
	private String username;
	
	
	public PostResponse(Post entity) {
		this.id=entity.getId();
		this.text=entity.getText();
		this.date=entity.getDate().toString();
		this.username=entity.getUser().getUserName();
	}
}
