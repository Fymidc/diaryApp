package com.example.mydaily.business.concretes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.QuestionService;
import com.example.mydaily.dataaccess.AnswerDao;
import com.example.mydaily.dataaccess.QuestionDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.QuestionCreateRequest;
import com.example.mydaily.dtos.QuestionUpdateRequest;
import com.example.mydaily.entities.Answers;
import com.example.mydaily.entities.Question;
import com.example.mydaily.entities.User;

@Service
public class QuestionManager implements QuestionService{
	private final QuestionDao questionDao;
	private final AnswerDao answerDao;
	
	
	public QuestionManager(QuestionDao questionDao,AnswerDao answerDao) {
		this.questionDao = questionDao;
		this.answerDao = answerDao;
		
	}

	@Override
	public List<Question> getAllQuestions(Optional<Long> questionid) {
		
		return questionDao.findAll();
	}

	@Override
	public Question createOneQuestion(QuestionCreateRequest question) {
		Question questionToSave = new Question();
		questionToSave.setId(question.getId());
		questionToSave.setText(question.getText());
		questionToSave.setDate(new Date());
		return questionDao.save(questionToSave);
	}

	@Override
	public void deleteOneQuestion(Long questionid) {
		questionDao.deleteById(questionid);
		
	}
/*
	@Override
	public List<Question> getOneQuestionPerDay(Optional<Long> userid) {
		
	
	 return questionDao.getOneQuestionIsAnswered(userid);

	 
	}
	*/
	@Override
	public List<Question> getOneQuestionPerDay(Long userid) {
		
		
		List<Question> list;
	
	   list =  questionDao.getOneQuestionIsAnswered(userid);
	   List<Long> questionid = list.stream().map(e->e.getId()).collect(Collectors.toList());
	   
	  List<Answers> foundedAnswer = answerDao.findByUserIdAndQuestionIdIn(userid,questionid);

	  if(foundedAnswer.isEmpty()) {
		  return list;
	  
	  }else if(list.stream().map(e->e.getId()) != foundedAnswer.stream().map(e->e.getQuestion().getId())) {
		  return questionDao.getOneQuestionIsAnswered(userid);
	  }
		  	
	  
	  return  null  ;
	  
	 
	}
	

	@Override
	public Question updateOneQuestionById(Long id ,QuestionUpdateRequest request) {
		Optional<Question> question = questionDao.findById(id);
		
		if(question.isPresent()) {
			Question updatedQuestion = question.get();
			updatedQuestion.setAnswered(request.isAnswered());
			
			return questionDao.save(updatedQuestion);
		}
		
		return null;
	}


	@Override
	public List<Question> getQuestionsByUserId(Long userid) {
		List<Question> list;
		
		   list =  questionDao.findAll();
		   List<Long> questionid = list.stream().map(e->e.getId()).collect(Collectors.toList());
		   
		  List<Answers> foundedAnswer = answerDao.findByUserIdAndQuestionIdIn(userid,questionid);

		  List<Long> foundedquestionId = foundedAnswer.stream().map(e->e.getQuestion().getId()).collect(Collectors.toList());
		  
		  if(foundedAnswer.isEmpty()) {
			  return null;
		  
		  }
		
		return questionDao.getAllQuestionByIdIn(foundedquestionId);
	
	}

}
