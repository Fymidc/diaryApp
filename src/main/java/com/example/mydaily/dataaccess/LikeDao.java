package com.example.mydaily.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mydaily.entities.Like;

public interface LikeDao extends JpaRepository<Like, Long> {

	List<Like> findByUserIdAndPostId(Long long1, Long long2);

	List<Like> findByUserId(Long long1);

	List<Like> findByPostId(Long long1);

	List<Like> findAllLikeByPostId(Long id);

	//@Query(value="select * from likes l where l.post_id =:postid",nativeQuery = true)
	List<Like> findAllUserIdByPostId(Long postid);

}
