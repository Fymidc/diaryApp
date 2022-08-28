package com.example.mydaily.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.mydaily.dtos.DiaryCreateRequest;
import com.example.mydaily.dtos.DiaryResponse;
import com.example.mydaily.dtos.DiaryUpdateRequest;
import com.example.mydaily.entities.Diary;

public interface DiaryService {

	List<DiaryResponse> getAllDiaries(Optional<Long> userid);
	Diary getOneDiaryById(Long diariesid);
	void deleteDiary(Long diariesid);
	Diary createOneDiary (DiaryCreateRequest newDiary);
	Diary updateOneDiary(Long diariesid,DiaryUpdateRequest request);
}
