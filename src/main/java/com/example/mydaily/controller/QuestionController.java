package com.example.mydaily.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydaily.business.abstracts.QuestionService;
import com.example.mydaily.dtos.QuestionCreateRequest;
import com.example.mydaily.dtos.QuestionUpdateRequest;
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
	public Question createOneQuestion(@RequestBody QuestionCreateRequest question ) {
		return questionsService.createOneQuestion(question);
	}
	
	@PutMapping("/{id}")
	public Question updateOneQuestion(@PathVariable Long id , @RequestBody QuestionUpdateRequest request) {
		return questionsService.updateOneQuestionById(id, request);
	}
	
	@GetMapping("/perday")
	public List<Question> getOneQuestionPerday(@RequestParam Long userid) {
		return questionsService.getOneQuestionPerDay(userid);
	}
	
	@GetMapping("/userq")
	public List<Question> getQuestionsByUserId(@RequestParam Long userid) {
		
		
		return questionsService.getQuestionsByUserId(userid);
	}
	
	/*
	@GetMapping("/perday/{id}")
	public List<Question> getOneQuestionPerday(@PathVariable Long userid) {
		
		return questionsService.getOneQuestionPerDay(userid);
		
		
	}
	*/
	
	/*
	@GetMapping("/perday")
	public Collection<Question> findByUserIdNotIn(@RequestParam(required=false,name="userid") Collection<Long> userid){
		return questionsService.findByUseridNotIn(userid);
	}
	
	*/
}
