package com.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity.model.Users;
import com.springSecurity.service.UserService;

@RestController
public class UserController {

	@Autowired
	private	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@PostMapping("/newUser")
	public Users register(@RequestBody Users user)
	{
		user.setPassword(encoder.encode(user.getPassword()));
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user)
	{
		
		return userService.verify(user);
	}
}
