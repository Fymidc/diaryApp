package com.example.mydaily.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.AnswersService;
import com.example.mydaily.dataaccess.AnswerDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.AnswerUpdateRequest;
import com.example.mydaily.entities.Answers;
import com.example.mydaily.entities.User;

@Service
public class AnswerManager implements AnswersService {
	
	private final AnswerDao answerDao;
	private final UserDao userdao;
	
	public AnswerManager(AnswerDao answerDao,UserDao userdao) {
		this.answerDao=answerDao;
		this.userdao=userdao;
	}

	@Override
	public List<Answers> getAllAnswers(Long userid) {
		Optional<User> user = userdao.findById(userid);
		
		if(user.isPresent()) {
			return answerDao.findByUserId(user.get().getId());
		}
		
		return null;
	}

	@Override
	public Answers getOneAnswerById(Long answerid) {
		return answerDao.findById(answerid).orElseThrow();

	}

	@Override
	public void deleteAnswer(Long answerid) {
		answerDao.deleteById(answerid);
		
	}

	@Override
	public Answers createOneAnswers(Answers newAnswer) {
		
		Optional<User> user = userdao.findById(newAnswer.getUser().getId());
		
		if(user.isPresent()) {
			Answers answer = new Answers();
		
			answer.setId(newAnswer.getId());
			answer.setText(newAnswer.getText());
			answer.setDate(new Date());
			answer.setIshidden(newAnswer.isIshidden());
			answer.setUser(user.get());
			
			return answerDao.save(answer);
			
		}
		
		return null;
	}

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

}
