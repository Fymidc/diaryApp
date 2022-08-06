package com.example.mydaily.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydaily.entities.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

	List<Comment> findByUserId(Long long1);

	List<Comment> findByPostId(Long long1);

	List<Comment> findByDiaryId(Long long1);

}
