package com.profound.main.service;

import com.profound.main.model.Blogger;

public interface BloggerService {
	
	public boolean registerBlogger(Blogger blogger);
	
	public Blogger loginAdmin(String email, String password);

}
