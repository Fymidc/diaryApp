package com.example.mydaily.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydaily.entities.Diary;

public interface DiaryDao extends JpaRepository<Diary, Long> {

	List<Diary> findByUserId(Long long1);

}
