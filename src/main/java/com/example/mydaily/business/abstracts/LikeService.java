package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.dtos.LikeCreateRequest;
import com.example.mydaily.dtos.LikeResponse;
import com.example.mydaily.entities.Like;

public interface LikeService {

	List<LikeResponse> getAllLikes(Optional<Long> userid,Optional<Long> postid);
	void deleteOneLike(Long userid);
	Like createOneLike(LikeCreateRequest request);
}
