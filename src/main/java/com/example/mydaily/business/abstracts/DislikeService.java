package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.dtos.DislikeResponse;
import com.example.mydaily.entities.Dislike;


public interface DislikeService {


	List<DislikeResponse> getAllDislikes(Optional<Long> userid,Optional<Long> postid);
	void deleteOneDisLike(Long userid);
	Dislike createOneDisLike(Dislike request);
}
