
package com.example.mydaily.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydaily.business.abstracts.DiaryService;
import com.example.mydaily.dtos.DiaryCreateRequest;
import com.example.mydaily.dtos.DiaryResponse;
import com.example.mydaily.dtos.DiaryUpdateRequest;
import com.example.mydaily.entities.Diary;


@RestController
@RequestMapping("/diaries")
public class DiaryController {

	private final DiaryService diaryService;
	
	public DiaryController(DiaryService diaryService) {
		this.diaryService=diaryService;
	}
	/*
	@GetMapping
	public List<DiaryResponse>getAllDiaries(@RequestParam Optional<Long> userid) {
	 return diaryService.getAllDiaries(userid);
	}
	*/
	
	@GetMapping
	public List<DiaryResponse>getAllDiaries(@RequestParam Optional<Long> userid) {
		if(userid.isPresent()) {
			return diaryService.getAllDiaries(userid);
		}else {
			return diaryService.getAllDiariesIfNotHidden();
		}
	 
	}
	
	@GetMapping("/{diaryid}")
	public Diary getOneDiaryById(@PathVariable Long diaryid) {
		return diaryService.getOneDiaryById(diaryid);
	}
	
	@DeleteMapping("/{id}")
	public void deleteDiary(@PathVariable Long id) {
		diaryService.deleteDiary(id);
	}
	
	@PostMapping
	public Diary createOneDiary(@RequestBody DiaryCreateRequest request) {
		return diaryService.createOneDiary(request);
	}
	
	@PutMapping("/{id}")
	public Diary updateOneDiary(@PathVariable Long id ,@RequestBody DiaryUpdateRequest request) {
		return diaryService.updateOneDiary(id, request);
	}
	
}
