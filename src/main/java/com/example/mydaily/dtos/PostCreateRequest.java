package com.example.mydaily.dtos;

import java.util.Date;

import com.example.mydaily.entities.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCreateRequest {

	private Long id;
	private String text;
	private Date date;
	private Long userid;
	
	PostCreateRequest(Post entity){
		this.id=entity.getId();
		this.text=entity.getText();
		this.date=entity.getDate();
		this.userid=entity.getUser().getId();
	}
	
}
