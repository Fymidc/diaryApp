package com.example.mydaily.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.QuestionService;
import com.example.mydaily.dataaccess.QuestionDao;
import com.example.mydaily.entities.Question;

@Service
public class QuestionManager implements QuestionService{
	private final QuestionDao questionDao;
	
	public QuestionManager(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public List<Question> getAllQuestions(Optional<Long> questionid) {
		
		return questionDao.findAll();
	}

	@Override
	public Question createOneQuestion(Question question) {
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

}
