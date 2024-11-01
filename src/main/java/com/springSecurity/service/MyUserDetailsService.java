package com.springSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springSecurity.model.UserPrincipal;
import com.springSecurity.model.Users;
import com.springSecurity.repository.UserRepository;


@Service
public class MyUserDetailsService  implements UserDetailsService{

	@Autowired
	private UserRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user=repo.findByUsername(username);
		
		if(user==null)
		{
			System.out.println("User not found ");
			throw  new UsernameNotFoundException("User not found");
		}
		return new UserPrincipal(user);
	}

}
