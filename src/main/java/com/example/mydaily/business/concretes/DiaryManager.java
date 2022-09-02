package com.example.mydaily.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.DiaryService;
import com.example.mydaily.dataaccess.DiaryDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.dtos.DiaryCreateRequest;
import com.example.mydaily.dtos.DiaryResponse;
import com.example.mydaily.dtos.DiaryUpdateRequest;
import com.example.mydaily.entities.Diary;
import com.example.mydaily.entities.User;

@Transactional
@Service
public class DiaryManager implements DiaryService {
	
	private final DiaryDao diaryDao;
	private final UserDao userDao;
	
	public DiaryManager(DiaryDao diaryDao,UserDao userDao) {
		this.diaryDao=diaryDao;
		this.userDao=userDao;
	}

	@Override
	public List<DiaryResponse> getAllDiaries(Optional<Long> userid) {
		List<Diary> list;
		if(userid.isPresent()) {
			list = diaryDao.findByUserId(userid);
		}else{
			list = diaryDao.findAll();
		}
		return list.stream().map(e-> new DiaryResponse(e)).collect(Collectors.toList());
	}

	@Override
	public List<DiaryResponse> getAllDiariesIfNotHidden() {
		List<Diary> list;
		list = diaryDao.findByIsHidden();
		
		return list.stream().map(e->new DiaryResponse(e)).collect(Collectors.toList());
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
	public Diary createOneDiary(DiaryCreateRequest newDiary) {
		Optional<User> user = userDao.findById(newDiary.getUserid());
		
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
			diaryToUpdate.setIshidden(request.isIshidden());
			
			return diaryDao.save(diaryToUpdate);
		}
		
		return null;
	}



}
