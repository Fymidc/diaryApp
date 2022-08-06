package com.example.mydaily.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydaily.entities.User;

public interface UserDao extends JpaRepository<User, Long> {
	User findByUserName(String username);
}
