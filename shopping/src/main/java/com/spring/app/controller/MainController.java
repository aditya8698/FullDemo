package com.spring.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.app.entity.User;
import com.spring.app.service.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService service;
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/registration")
	public String getRegister() {
		return "register";
	}
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute User user,HttpSession session) {
		service.registerUser(user);
		session.setAttribute("message", "You Have Successfully Registered");
		return "register";
	}
	
	@GetMapping("/homePage")
	public String getHomePage() {
		return "index";
	}
	
}
