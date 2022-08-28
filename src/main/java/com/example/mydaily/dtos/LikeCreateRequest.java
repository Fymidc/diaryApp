package com.example.mydaily.dtos;

import com.example.mydaily.entities.Like;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeCreateRequest {

	private Long id;
	private Long userid;
	private Long postid;
	
	public LikeCreateRequest(Like entity) {
		this.id=entity.getId();
		this.userid=entity.getUser().getId();
		this.postid=entity.getPost().getId();
	}
}
