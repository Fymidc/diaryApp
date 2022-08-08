package com.example.mydaily.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydaily.business.abstracts.AnswersService;
import com.example.mydaily.dtos.AnswerUpdateRequest;
import com.example.mydaily.entities.Answers;

@RestController
@RequestMapping("/answers")
public class AnswerController {

	private final AnswersService answersService;
	
	public AnswerController(AnswersService answersService) {
		this.answersService=answersService;
		
	}
	
	@GetMapping("/{id}")
	public List<Answers> getAllAnswers(@PathVariable Long id){
		return answersService.getAllAnswers(id);
	}
	
	@GetMapping("/oneanswer/{id}")
	public Answers getOneAnswerById(@PathVariable Long id ) {
		return answersService.getOneAnswerById(id);
	}
	
	@DeleteMapping
	void deleteAnswer(@PathVariable Long id ) {
		answersService.deleteAnswer(id);
	}
	
	@PostMapping
	public Answers createOneAnswers(@RequestBody Answers request) {
		return answersService.createOneAnswers(request);
	}
	
	@PutMapping("/{id}")
	public Answers updateOneAnswer(@PathVariable Long id ,@RequestBody AnswerUpdateRequest request) {
		return answersService.updateOneAnswer(id, request);
	}
}
