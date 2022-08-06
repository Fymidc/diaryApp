package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.dtos.CommentResponse;
import com.example.mydaily.dtos.CommentUpdateRequest;
import com.example.mydaily.entities.Comment;

public interface CommentService {

	List<CommentResponse> getAllComments(Optional<Long> userid,Optional<Long> postid,Optional<Long> diaryid);

    void deleteOneComment(Long commentid);

    Comment createOneComment(Comment request);

    Comment updateOneComment(Long commentid, CommentUpdateRequest request);
	
}
