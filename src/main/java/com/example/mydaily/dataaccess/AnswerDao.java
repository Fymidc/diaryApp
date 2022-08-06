package com.example.mydaily.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydaily.entities.Answers;


public interface AnswerDao extends JpaRepository<Answers, Long> {

	List<Answers> findByUserId(Long long1);

}
