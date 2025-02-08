package com.profound.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.profound.main.model.Blogger;
import com.profound.main.model.Post;
import com.profound.main.service.PostService;

@Controller
@SessionAttributes("blogger") //Tells Spring to store the "blogger" attribute in the session.
@RequestMapping("/posts")
public class BlogController {


	@Autowired
	private PostService postService;

	@GetMapping
	public String getAllPosts(@ModelAttribute("blogger") Blogger blogger, Model model) {
		model.addAttribute("posts", postService.getAllPosts());
		model.addAttribute("bloggerEmail", blogger.getEmail());
		return "/home";
	}

	@GetMapping("/add")
	public String showCreateForm(@ModelAttribute("blogger") Blogger blogger,Model model) {
		model.addAttribute("post", new Post());
		model.addAttribute("bloggerEmail", blogger.getEmail());
		return "/addBlog";
	}

	@PostMapping
	public String createPost(@ModelAttribute Post post) {
		postService.createPost(post);
		return "redirect:/posts";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@ModelAttribute("blogger") Blogger blogger,@PathVariable Long id, Model model) {
		model.addAttribute("post", postService.getPostById(id));
		model.addAttribute("bloggerEmail", blogger.getEmail());
		return "/editBlog";
	}
	
	@GetMapping("/view/{id}")
	public String showPost(@ModelAttribute("blogger") Blogger blogger,@PathVariable Long id, Model model) {
		model.addAttribute("post", postService.getPostById(id));
		return "/viewBlog";
	}

	@PostMapping("/update/{id}")
	public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
		postService.updatePost(id, post);
		return "redirect:/posts";
	}


	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		return "redirect:/posts";
	}
	

}
