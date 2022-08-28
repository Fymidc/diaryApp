package com.example.mydaily.dtos;



import com.example.mydaily.entities.Diary;
import com.example.mydaily.entities.Post;

import lombok.Data;

@Data
public class DiaryResponse {

	private Long id;
	private String text;
	private String date;
	private String username;
	private boolean ishidden;
	
	public DiaryResponse(Diary entity) {
		this.id=entity.getId();
		this.text=entity.getText();
		this.date=entity.getDate().toString();
		this.username=entity.getUser().getUserName();
		this.ishidden=entity.isIshidden();
		
	}
}
