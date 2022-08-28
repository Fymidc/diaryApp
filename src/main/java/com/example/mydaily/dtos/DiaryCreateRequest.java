package com.example.mydaily.dtos;

import java.util.Date;

import com.example.mydaily.entities.Diary;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiaryCreateRequest {

	private Long id;
	private String text;
	private Date date;
	private Long userid;
	private boolean ishidden;
	
	DiaryCreateRequest(Diary entity){
		this.id=entity.getId();
		this.text=entity.getText();
		this.date=entity.getDate();
		this.userid=entity.getUser().getId();
		this.ishidden=entity.isIshidden();
		
		
	}
	
}
