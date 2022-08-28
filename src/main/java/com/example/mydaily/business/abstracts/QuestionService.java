package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.dtos.QuestionCreateRequest;
import com.example.mydaily.dtos.QuestionUpdateRequest;
import com.example.mydaily.entities.Question;

public interface QuestionService {
	//getonequestionbyquestionidandanswerid(optional<Long> questionid , optional<Long> answerid)
	List<Question> getAllQuestions(Optional<Long> questionid);
	Question createOneQuestion(QuestionCreateRequest question);
	void deleteOneQuestion(Long questionid);
	List<Question> getOneQuestionPerDay ();
	Question updateOneQuestionById(Long questionid,  QuestionUpdateRequest request);
}
