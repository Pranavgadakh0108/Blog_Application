package com.profound.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.profound.main.model.Blogger;
import com.profound.main.service.BloggerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("blogger") //Tells Spring to store the "blogger" attribute in the session.
@RequestMapping("/")
public class BloggerController {

	@Autowired
	private BloggerService bloggerService;

	@GetMapping("/regPage")
	public String openRegPage(Model model) {
		model.addAttribute("blogger", new Blogger());
		return "register";
	}

	@PostMapping("/regForm")
	public String submitRegForm(@ModelAttribute("blogger") Blogger blogger, Model model) {
		boolean status = bloggerService.registerBlogger(blogger);

		if (status) {
			model.addAttribute("successMsg", "Blogger Registered Successfully!!");
			return "login";
		} else {
			model.addAttribute("errorMsg", "Blogger NOT Registered due to some error!!");
			return "register";
		}

	}

	@GetMapping("/")
	public String openLoginPage(Model model) {
		model.addAttribute("blogger", new Blogger());
		return "login";
	}

	@PostMapping("/loginForm")
	public String submitLoginForm(@ModelAttribute("blogger") Blogger blogger, Model model) {
		Blogger validBlogger = bloggerService.loginAdmin(blogger.getEmail(), blogger.getPassword());

		if (validBlogger != null) {
			model.addAttribute("bloggername", validBlogger.getName());
			//System.out.println(validBlogger.getName());
			return "redirect:/posts";
		} else {
			model.addAttribute("errorMsg", "Email id & password didn't match..!!");
			return "login";
		}
	}

	@GetMapping("/Logout")
	public String Logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		return "redirect:/";
	}

}
