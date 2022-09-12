package com.example.mydaily.dataaccess;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydaily.entities.Answers;


public interface AnswerDao extends JpaRepository<Answers, Long> {

	List<Answers> findByUserId(Long long1);
	List<Answers> findByUserIdAndQuestionIdIn( Long questionid,List<Long> questionid2);
	List<Answers> findByUserIdAndQuestionId( Long userid , Long questionid);

}
