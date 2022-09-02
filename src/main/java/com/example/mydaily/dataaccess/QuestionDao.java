package com.example.mydaily.dataaccess;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mydaily.entities.Question;

public interface QuestionDao extends JpaRepository<Question, Long> {
	
	//select random unanswered one question
	//@Query(value="select * from question q where q.is_answered='false' order by random() limit 1 ", nativeQuery = true)
	@Query(value="select * from question order by random() limit 1",nativeQuery = true)
	List<Question> getOneQuestionIsAnswered(Long userid);
	//Collection<Question>findByUseridNotIn(Collection<Long> userid);
	List<Question> getAllQuestionByIdIn(List<Long>questionid);
}
