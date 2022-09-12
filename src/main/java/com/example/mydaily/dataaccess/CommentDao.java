package com.example.mydaily.dataaccess;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mydaily.entities.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

	List<Comment> findByUserId(Long long1);

	List<Comment> findByPostId(Long long1);

	List<Comment> findByDiaryId(Long long1);

	//List<Comment> findByPostIdIn(List<Long> postids);
	//@Query(value="select count(*) from comment c where c.post_id in == 1",nativeQuery = true)
	List<Comment> findAllCommentByPostId(Long id);

	List<Comment> findAllCommentByDiaryId(Long id);

}
