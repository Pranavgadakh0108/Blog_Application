package com.profound.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profound.main.model.Post;
import com.profound.main.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        //return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    	 Optional<Post> optional= postRepository.findById(id);
    	 return optional.get();
    }
    
    

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, Post postDetails) {
        Post post = getPostById(id);
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        return postRepository.save(post);
    }

	@Override
	public void deletePost(Long id) {
		postRepository.deleteById(id);
		
	}

}
