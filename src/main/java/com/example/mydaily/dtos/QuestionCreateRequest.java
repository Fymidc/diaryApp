package com.example.mydaily.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class QuestionCreateRequest {
	private Long id;
	private String text;
	private Date date;
}
