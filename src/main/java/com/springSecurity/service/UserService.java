package com.springSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.springSecurity.model.Users;
import com.springSecurity.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	
	public Users register(Users user) {
		return this.repo.save(user);
	}

	public String verify(Users user) {
		Authentication authentication=
				authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (authentication.isAuthenticated())
		return jwtService.generateToken(user.getUsername());
		else 
		return "failure";
	}
	
}
