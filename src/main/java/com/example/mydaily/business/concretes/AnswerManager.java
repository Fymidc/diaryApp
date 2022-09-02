package com.example.mydaily.business.concretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.AnswersService;
import com.example.mydaily.dataaccess.AnswerDao;
import com.example.mydaily.dataaccess.QuestionDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.AnswerCreateRequest;
import com.example.mydaily.dtos.AnswerUpdateRequest;
import com.example.mydaily.entities.Answers;
import com.example.mydaily.entities.Question;
import com.example.mydaily.entities.User;

@Service
public class AnswerManager implements AnswersService {
	
	private final AnswerDao answerDao;
	private final UserDao userdao;
	private final QuestionDao questiondao;
	
	public AnswerManager(AnswerDao answerDao,UserDao userdao,QuestionDao questiondao) {
		this.answerDao=answerDao;
		this.userdao=userdao;
		this.questiondao=questiondao;
	}
/*
	@Override
	public List<Answers> getAllAnswers(Long userid) {
		Optional<User> user = userdao.findById(userid);
		
		if(user.isPresent()) {
			return answerDao.findByUserId(user.get().getId());
		}
		
		return null;
	}
	*/

	@Override
	public Answers getOneAnswerById(Long answerid ) {
		
		return answerDao.findById(answerid).orElseThrow();

	}

	@Override
	public void deleteAnswer(Long answerid) {
		answerDao.deleteById(answerid);
		
	}

	@Override
	public Answers createOneAnswers(AnswerCreateRequest newAnswer) {
		
		Optional<User> user = userdao.findById(newAnswer.getUserid());
		Optional<Question> question = questiondao.findById(newAnswer.getQuestionid());
		List<Answers> uanswer = answerDao.findByUserId(newAnswer.getUserid());
		
		
		if(user.isPresent() && question.isPresent() ) {
			Answers answer = new Answers();
		
			answer.setId(newAnswer.getId());
			answer.setText(newAnswer.getText());
			answer.setDate(new Date());
			answer.setIshidden(newAnswer.isIshidden());
			answer.setUser(user.get());
			answer.setQuestion( question.get());
			
			
			Question answeredQuestion = question.get();
			if(uanswer.stream().map(e-> e.getUser() == user.get()) != null) {

				answeredQuestion.setAnswered(true);
			}else {
				answeredQuestion.setAnswered(false);
			}
			
			//questiona list ekle methodu 
			//list = list.add olarak yazmayı dene 
			/*
			if(list.contains(user.get().getId())) {
				answeredQuestion.setAnswered(true);
			}else {
				answeredQuestion.setAnswered(false);
			}
			*/
			
			return answerDao.save(answer);
			
		}
		
		return null;
	}
/*
	@Override
	public Answers updateOneAnswer(Long answerid, AnswerUpdateRequest request) {
		Optional<Answers> answer = answerDao.findById(answerid);
		
		if(answer.isPresent()) {
			Answers answerToUpdate = answer.get();
			
			answerToUpdate.setText(request.getText());
			return answerDao.save(answerToUpdate);
		}
		
		return null;
	}

 	*/
}
