package com.profound.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profound.main.model.Blogger;

public interface BloggerRepository extends JpaRepository<Blogger, Integer>{
	
	Blogger findByEmail(String email);

}
