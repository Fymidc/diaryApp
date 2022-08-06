package com.example.mydaily.business.abstracts;

import java.util.List;


import com.example.mydaily.dtos.AnswerUpdateRequest;
import com.example.mydaily.entities.Answers;



public interface AnswersService {

	List<Answers> getAllAnswers(Long userid);
	Answers getOneAnswerById(Long answerid);
	void deleteAnswer(Long answerid);
	Answers createOneAnswers(Answers newAnswer);
	Answers updateOneAnswer(Long answerid , AnswerUpdateRequest request);
}
