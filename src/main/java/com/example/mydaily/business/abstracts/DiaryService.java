package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.dtos.DiaryUpdateRequest;
import com.example.mydaily.entities.Diary;

public interface DiaryService {

	List<Diary> getAllDiaries(Optional<Long> userid);
	Diary getOneDiaryById(Long diariesid);
	void deleteDiary(Long diariesid);
	Diary createOneDiary (Diary newDiary);
	Diary updateOneDiary(Long diariesid,DiaryUpdateRequest request);
}
