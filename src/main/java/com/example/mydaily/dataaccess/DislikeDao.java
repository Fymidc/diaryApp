package com.example.mydaily.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydaily.entities.Dislike;


public interface DislikeDao extends JpaRepository<Dislike, Long> {

	List<Dislike> findByUserIdAndPostId(Long long1, Long long2);

	List<Dislike> findByUserId(Long long1);

	List<Dislike> findByPostId(Long long1);

}
