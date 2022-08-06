package com.example.mydaily.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydaily.business.abstracts.QuestionService;
import com.example.mydaily.entities.Question;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	private final QuestionService questionsService;
	
	public QuestionController(QuestionService questionService) {
		this.questionsService=questionService;
	}
	
	@GetMapping
	public List<Question> getAllQuestions(@RequestParam Optional<Long> questionid){
		return questionsService.getAllQuestions(questionid);
	}
	
	@PostMapping
	public Question createOneQuestion(@RequestBody Question question ) {
		return questionsService.createOneQuestion(question);
	}
	
}
