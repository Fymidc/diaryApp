package com.example.mydaily.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.DiaryService;
import com.example.mydaily.dataaccess.DiaryDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.DiaryUpdateRequest;
import com.example.mydaily.entities.Diary;
import com.example.mydaily.entities.User;

@Service
public class DiaryManager implements DiaryService {
	
	private final DiaryDao diaryDao;
	private final UserDao userDao;
	
	public DiaryManager(DiaryDao diaryDao,UserDao userDao) {
		this.diaryDao=diaryDao;
		this.userDao=userDao;
	}

	@Override
	public List<Diary> getAllDiaries(Optional<Long> userid) {
		
		if(userid.isPresent()) {
			return diaryDao.findByUserId(userid.get());
		}
		return diaryDao.findAll();
	}

	@Override
	public Diary getOneDiaryById(Long diariesid) {
		
		return diaryDao.findById(diariesid).orElseThrow();
	}

	@Override
	public void deleteDiary(Long diariesid) {
		diaryDao.deleteById(diariesid);
		
	}

	@Override
	public Diary createOneDiary(Diary newDiary) {
		Optional<User> user = userDao.findById(newDiary.getUser().getId());
		
		if(user.isPresent()) {
			Diary diary = new Diary();
			
			diary.setId(newDiary.getId());
			diary.setText(newDiary.getText());
			diary.setUser(user.get());
			diary.setDate(new Date());
			diary.setIshidden(newDiary.isIshidden());
			
			return diaryDao.save(diary);
		}
		
		return null;
	}

	@Override
	public Diary updateOneDiary(Long diariesid, DiaryUpdateRequest request) {
		
		Optional<Diary> findeddiary = diaryDao.findById(diariesid);
		
		if(findeddiary.isPresent()) {
			Diary diaryToUpdate = findeddiary.get();
			diaryToUpdate.setText(request.getText());
			diaryToUpdate.setIshidden(request.isIshidden());
			
			return diaryDao.save(diaryToUpdate);
		}
		
		return null;
	}

}
