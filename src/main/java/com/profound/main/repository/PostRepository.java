package com.profound.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profound.main.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
