package com.example.mydaily.dataaccess;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydaily.entities.Post;

public interface PostDao extends JpaRepository<Post, Long>{

	List<Post> findByUserId(Optional<Long> userid);

}
