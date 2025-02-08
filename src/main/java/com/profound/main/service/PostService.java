package com.profound.main.service;

import java.util.List;

import com.profound.main.model.Post;

public interface PostService {
	
	List<Post> getAllPosts();
    Post getPostById(Long id);
    Post createPost(Post post);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);


}
