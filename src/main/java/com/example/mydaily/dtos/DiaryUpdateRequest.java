package com.example.mydaily.dtos;

import lombok.Data;

@Data
public class DiaryUpdateRequest {
	private String text;
	private boolean ishidden;
	
}
