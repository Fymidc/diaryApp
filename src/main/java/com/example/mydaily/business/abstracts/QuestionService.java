package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.entities.Question;

public interface QuestionService {
	List<Question> getAllQuestions(Optional<Long> questionid);
	Question createOneQuestion(Question question);
	void deleteOneQuestion(Long questionid);
}
