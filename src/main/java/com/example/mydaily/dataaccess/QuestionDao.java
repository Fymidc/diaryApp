package com.example.mydaily.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mydaily.entities.Question;

public interface QuestionDao extends JpaRepository<Question, Long> {
	
	//select random unanswered one question
	@Query(value="select * from question q where q.is_answered='false' order by random() limit 1 ", nativeQuery = true)
	List<Question> getOneQuestionIsAnswered();
}
