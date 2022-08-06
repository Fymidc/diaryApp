package com.example.mydaily.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydaily.entities.Question;

public interface QuestionDao extends JpaRepository<Question, Long> {

}
