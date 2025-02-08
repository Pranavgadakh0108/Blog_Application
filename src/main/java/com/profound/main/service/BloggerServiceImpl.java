package com.profound.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profound.main.model.Blogger;
import com.profound.main.repository.BloggerRepository;

@Service
public class BloggerServiceImpl implements BloggerService{
	
	@Autowired
	BloggerRepository bloggerRepository;

	@Override
	public boolean registerBlogger(Blogger blogger) {
		try
		{
			bloggerRepository.save(blogger);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Blogger loginAdmin(String email, String password) {
		Blogger validBlogger = bloggerRepository.findByEmail(email);
		if(validBlogger != null && validBlogger.getPassword().equals(password))
		{
			return validBlogger;
		}
		return null;
	}

}
