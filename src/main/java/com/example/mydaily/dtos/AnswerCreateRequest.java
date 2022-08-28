package com.example.mydaily.dtos;

import java.util.Date;

import com.example.mydaily.entities.Answers;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerCreateRequest {

	private Long id;
	private String text;
	private Date date;
	private boolean ishidden;
	private Long userid;
	private Long questionid;
	
	public AnswerCreateRequest(Answers entity) {
	
		this.id = entity.getId();
		this.text = entity.getText();
		this.date = entity.getDate();
		this.ishidden = entity.isIshidden();
		this.userid = entity.getUser().getId();
		this.questionid = entity.getQuestion().getId();
	}
	
	
	
	
	
}
