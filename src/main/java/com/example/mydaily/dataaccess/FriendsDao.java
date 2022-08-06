package com.example.mydaily.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydaily.entities.Friends;

public interface FriendsDao extends JpaRepository<Friends, Long> {

	Friends findByFriendsName(String friendsName);

	List<Friends> findByUserId(Long userid);

}
