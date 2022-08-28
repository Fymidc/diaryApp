package com.example.mydaily.dataaccess;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydaily.entities.Diary;

public interface DiaryDao extends JpaRepository<Diary, Long> {

	List<Diary> findByUserId(Optional<Long> long1);

	Diary findById(Optional<Long> diaryid);

}
