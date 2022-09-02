package com.example.mydaily.business.abstracts;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.mydaily.dtos.QuestionCreateRequest;
import com.example.mydaily.dtos.QuestionUpdateRequest;
import com.example.mydaily.entities.Question;

public interface QuestionService {
	//getonequestionbyquestionidandanswerid(optional<Long> questionid , optional<Long> answerid)
	List<Question> getAllQuestions(Optional<Long> questionid);
	Question createOneQuestion(QuestionCreateRequest question);
	void deleteOneQuestion(Long questionid);
	List<Question> getOneQuestionPerDay (Long userid);
	Question updateOneQuestionById(Long questionid,  QuestionUpdateRequest request);
	List<Question> getQuestionsByUserId(@RequestParam Long userid);
	//Collection<Question>findByUseridNotIn(Collection<Long> userid);
}
