package com.example.mydaily.business.abstracts;

import java.util.List;

import com.example.mydaily.dtos.AnswerCreateRequest;
import com.example.mydaily.dtos.AnswerUpdateRequest;
import com.example.mydaily.entities.Answers;



public interface AnswersService {

	//List<Answers> getAllAnswers(Long userid);
	Answers getOneAnswerById(Long answerid);
	List<Answers> getAllAnswersByUserIdAndQuestionId(Long userid,Long questionid);
	void deleteAnswer(Long answerid);
	Answers createOneAnswers(AnswerCreateRequest newAnswer);
	//Answers updateOneAnswer(Long answerid , AnswerUpdateRequest request);
}
