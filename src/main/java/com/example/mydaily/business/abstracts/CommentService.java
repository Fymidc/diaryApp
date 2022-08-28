package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.dtos.CommentCreateRequest;
import com.example.mydaily.dtos.CommentResponseDiary;
import com.example.mydaily.dtos.CommentResponsePost;
import com.example.mydaily.dtos.CommentUpdateRequest;
import com.example.mydaily.entities.Comment;

public interface CommentService {

	List<CommentResponsePost> getAllCommentsPost(Optional<Long> userid,Optional<Long> postid);

	List<CommentResponseDiary> getAllCommentsDiary(Optional<Long> userid,Optional<Long> diaryid);
	
    void deleteOneComment(Long commentid);

    Comment createOneComment(Optional<Long > postid,Optional<Long > diaryid ,CommentCreateRequest request);

    Comment updateOneComment(Long commentid, CommentUpdateRequest request);
	
}
